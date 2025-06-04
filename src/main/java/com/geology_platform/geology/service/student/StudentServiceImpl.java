package com.geology_platform.geology.service.student;

import com.geology_platform.geology.dto.student.BrancheDto;
import com.geology_platform.geology.dto.student.FormationDto;
import com.geology_platform.geology.entity.student.Branche;
import com.geology_platform.geology.entity.student.Formation;
import com.geology_platform.geology.entity.student.Student;
import com.geology_platform.geology.entity.teacher.AccountValidationCode;
import com.geology_platform.geology.entity.user.Authority;
import com.geology_platform.geology.entity.user.Users;
import com.geology_platform.geology.exception.global.ResourceNotFound;
import com.geology_platform.geology.exception.student.StudentAlreadyExist;
import com.geology_platform.geology.exception.student.WrongVerificationCode;
import com.geology_platform.geology.mapper.student.BrancheMapper;
import com.geology_platform.geology.mapper.student.FormationMapper;
import com.geology_platform.geology.projection.StudentProjection;
import com.geology_platform.geology.repository.student.BrancheRepo;
import com.geology_platform.geology.repository.student.FormationRepo;
import com.geology_platform.geology.repository.student.StudentRepo;
import com.geology_platform.geology.repository.teacher.CodeGenerationRepo;
import com.geology_platform.geology.repository.user.AuthorityRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private FormationRepo formationRepo;
    private BrancheRepo brancheRepo;
    private FormationMapper formationMapper;
    private BrancheMapper brancheMapper;
    private CodeGenerationRepo codeGenerationRepo;
    private AuthorityRepo authorityRepo;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); // this one uses bcrypt by default




    @Transactional
    @Override
    public void addFormation(FormationDto formationDto) {
        formationRepo.save(formationMapper.toEntity(formationDto));
    }

    @Transactional
    @Override
    public void addBranche(Long formationId, BrancheDto brancheDto) {
        Formation formation = formationRepo.findById(formationId).orElseThrow(()-> new ResourceNotFound("Formation not found",formationId));

        Branche branche = brancheMapper.toEntity(brancheDto);

        formation.addBranche(branche);

        brancheRepo.save(branche);

    }

    @Override
    public List<FormationDto> loadFormations() {
        return formationMapper.toResponseList(formationRepo.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<BrancheDto> loadBranches(String formationName) {
        Formation formation = formationRepo.findByName(formationName).orElseThrow(()->new ResourceNotFound("Formation not found",formationName));
        return brancheMapper.toResponseList(formation.getBrancheList());
    }

    @Transactional
    @Override
    public void addStudent(String branchName, Student student, String verificationCode) {
        AccountValidationCode validationCode = codeGenerationRepo.getByCode(verificationCode).orElseThrow(()->new WrongVerificationCode(verificationCode));


        Date now = new Date();

        Date expiredCodeDate = validationCode.getExpiredDate();

        if(now.after(expiredCodeDate)){
            throw new WrongVerificationCode("expired code");
        }

        if(studentRepo.existsByEmail(student.getEmail())){
            throw new StudentAlreadyExist(student.getEmail());
        }

        Branche branche = brancheRepo.findByName(branchName).orElseThrow(()->new ResourceNotFound("Branche not found",branchName));

        Users userInfo = new Users();


        Authority authority = authorityRepo.getAuthoritiesByAuthority("STUDENT");


        if(authority == null){
            authority = new Authority("STUDENT");
        }


        userInfo.setPassword(encodePassword(student.getPassword()));
        userInfo.setUsername(student.getEmail());


        authorityRepo.save(authority);
        userInfo.addAuhority(authority);



        student.setUserInfo(userInfo);

        branche.addStudent(student);

        brancheRepo.save(branche);

    }

    @Override
    public Long countStudent() {
        return studentRepo.count();
    }

    @Override
    public void removeStudent(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(()->new ResourceNotFound("Student not found",id));

        studentRepo.delete(student);
    }

    @Override
    public List<StudentProjection> loadAllStudents(Integer page, Integer size, Long brancheId) {

        if(page < 0) page = 0;
        if(size < 0 ) size = 10;

        Pageable pageable = PageRequest.of(page,size);

        Page<StudentProjection> studentPage;

        if(brancheId != null){
            studentPage = studentRepo.findAllByBrancheIdOrderByIdDesc(pageable,brancheId);
        }else {
            studentPage = studentRepo.findAllByOrderByIdDesc(pageable);
        }

        return studentPage.getContent();
    }

    @Transactional
    @Override
    public void deleteAllFormation() {
        formationRepo.deleteAll();
    }


    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}

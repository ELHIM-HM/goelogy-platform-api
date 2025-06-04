package com.geology_platform.geology.service.teacher;

import com.geology_platform.geology.dto.teacher.GradeDto;
import com.geology_platform.geology.entity.teacher.AccountValidationCode;
import com.geology_platform.geology.entity.teacher.Grade;
import com.geology_platform.geology.entity.teacher.Teacher;
import com.geology_platform.geology.entity.user.Authority;
import com.geology_platform.geology.entity.user.Users;
import com.geology_platform.geology.exception.global.ResourceNotFound;
import com.geology_platform.geology.exception.teacher.TeacherAlreadyExists;
import com.geology_platform.geology.mapper.teacher.GradeMapper;
import com.geology_platform.geology.projection.TeacherProjection;
import com.geology_platform.geology.repository.teacher.CodeGenerationRepo;
import com.geology_platform.geology.repository.teacher.GradeRepo;
import com.geology_platform.geology.repository.teacher.TeacherRepo;
import com.geology_platform.geology.repository.user.AuthorityRepo;
import com.geology_platform.geology.repository.user.UsersRepo;
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
public class TeacherServiceImpl implements TeacherService {

    private GradeRepo gradeRepo;
    private GradeMapper gradeMapper;
    private TeacherRepo teacherRepo;
    private CodeGenerationRepo codeGenerationRepo;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); // this one uses bcrypt by default
    private AuthorityRepo authorityRepo;



    @Transactional
    @Override
    public void addGrade(GradeDto gradeDto) {
        gradeRepo.save(gradeMapper.toEntity(gradeDto));
    }

    @Override
    public List<GradeDto> loadGrades() {
        return gradeMapper.toResponseList(gradeRepo.findAll());
    }

    @Transactional
    @Override
    public void addTeacher(String gradeName,Teacher teacher) {

        if(teacherRepo.existsByEmail(teacher.getEmail())){
            throw new TeacherAlreadyExists(teacher.getEmail());
        }

        Grade grade = gradeRepo.findByName(gradeName).orElseThrow(()->new ResourceNotFound("Grade Not Found",gradeName));

        Users userInfo = new Users();
        Authority authority = authorityRepo.getAuthoritiesByAuthority("TEACHER");

        if(authority == null){
            authority = new Authority("TEACHER");

        }

        userInfo.setPassword(encodePassword(teacher.getPassword()));
        userInfo.setUsername(teacher.getEmail());


        userInfo.addAuhority(authority);




        teacher.setUserInfo(userInfo);

        grade.addTeacher(teacher);

        teacherRepo.save(teacher);
    }

    @Transactional
    @Override
    public void validateTeacher(Long id,boolean isValidated) {

        if(isValidated){
            Teacher teacher = teacherRepo.findById(id).orElseThrow(()->new ResourceNotFound("Teacher not found",id));
            teacher.setValidated(true);
            teacherRepo.save(teacher);
        }

    }

    @Override
    public Long teachersCount() {
        return teacherRepo.count();
    }

    @Override
    public List<TeacherProjection> loadTeachers(Integer page, Integer size, Long gradeId) {

        if(page < 0) page = 0;
        if(size < 0) size = 10;

        Pageable pageRequest = PageRequest.of(page,size);

        Page<TeacherProjection> result;

        if(gradeId!=null){
            result = teacherRepo.findAllByGradeId(pageRequest,gradeId);
        }else {
            result = teacherRepo.findAllByOrderByIdDesc(pageRequest);
        }

        return result.getContent();
    }

    @Override
    public String generateValidationCode(Long duration) {

        AccountValidationCode accountValidationCode = new AccountValidationCode();

        accountValidationCode.setDuration(duration);

        accountValidationCode.setExpiredDate(new Date(System.currentTimeMillis() + duration * 1000));

        codeGenerationRepo.save(accountValidationCode);

        System.out.println("code => "+accountValidationCode.getCode());
        System.out.println("id => "+accountValidationCode.getId());

        return accountValidationCode.getCode();
    }


    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

}

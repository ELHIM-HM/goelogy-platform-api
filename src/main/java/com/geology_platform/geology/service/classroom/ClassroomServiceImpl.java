package com.geology_platform.geology.service.classroom;

import com.geology_platform.geology.dto.classroom.*;
import com.geology_platform.geology.entity.classroom.ClassMaterial;
import com.geology_platform.geology.entity.classroom.ClassSubject;
import com.geology_platform.geology.entity.classroom.Classroom;
import com.geology_platform.geology.entity.student.Student;
import com.geology_platform.geology.entity.teacher.Teacher;
import com.geology_platform.geology.exception.classroom.NotValidClassCode;
import com.geology_platform.geology.exception.classroom.StudentAlreadyJoined;
import com.geology_platform.geology.exception.student.StudentAlreadyExist;
import com.geology_platform.geology.mapper.classroom.ClassSubjectMapper;
import com.geology_platform.geology.mapper.classroom.ClassroomMapper;
import com.geology_platform.geology.mapper.classroom.MaterialMapper;
import com.geology_platform.geology.repository.classroom.ClassMaterialRepo;
import com.geology_platform.geology.repository.classroom.ClassSubjectRepo;
import com.geology_platform.geology.repository.classroom.ClassroomRepo;
import com.geology_platform.geology.repository.student.StudentRepo;
import com.geology_platform.geology.repository.teacher.TeacherRepo;
import com.geology_platform.geology.service.CodeGeneratorService;
import com.geology_platform.geology.service.fileUpload.FileUploadServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/

@Service
@AllArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private ClassroomRepo classroomRepo;
    private ClassroomMapper classroomMapper;
    private TeacherRepo teacherRepo;
    private StudentRepo studentRepo;
    private FileUploadServiceImpl fileUploadService;
    private ClassSubjectRepo classSubjectRepo;
    private ClassMaterialRepo classMaterialRepo;
    private CodeGeneratorService codeGeneratorService;

    private ClassSubjectMapper classSubjectMapper;
    private MaterialMapper materialMapper;



    @Override
    @Transactional
    public void addClassroom(ClassroomDto classroomDto,MultipartFile coverImage) throws IOException {
        Teacher teacher = teacherRepo.findByEmail(classroomDto.getTeacherEmail());

        Classroom classroom = classroomMapper.toEntity(classroomDto);


        if(coverImage !=null){

        classroom.setImage(fileUploadService.uploadFileToFileSystem(coverImage));
        }

        classroom.setJoinCode(codeGeneratorService.generateUniqueJoinCode());

        teacher.addClassroom(classroom);

        teacherRepo.save(teacher);

    }

    @Override
    @Transactional
    public void joinClassroom(JoinClassroomDto joinClassroomDto) {
        Classroom classroom = classroomRepo.findByJoinCode(joinClassroomDto.getClassCode()).orElseThrow(()->new NotValidClassCode(joinClassroomDto.getClassCode()));

        Student student =  studentRepo.findByEmail(joinClassroomDto.getStudentEmail()).orElseThrow();

        for(int i = 0; i<classroom.getStudentList().size() ; i++){
            if(classroom.getStudentList().contains(student)){
                throw new StudentAlreadyJoined(student.getId());
            }
        }

        classroom.addStudentToClass(student);

        classroomRepo.save(classroom);

    }



    @Override
    public List<ClassroomInfoDto> laodStudentClassrooms(String email) {
        return studentRepo.findClassroomInfoByStudentEmail(email);
    }


    @Transactional
    @Override
    public void addSubject(Long id,AddSubjectDto subjectDto) {
        Classroom classroom = classroomRepo.findById(id).orElseThrow();

        if(classSubjectRepo.existsByName(subjectDto.getName())){
            throw new DuplicateFormatFlagsException("subject is already exist");
        }

        ClassSubject subject = new ClassSubject();

        subject.setName(subjectDto.getName());

        classroom.addSubject(subject);

        classroomRepo.save(classroom);

    }


    @Transactional(readOnly = true)
    @Override
    public Set<ReadSubjectDto> loadAllSubjects(Long classId) {

        Classroom classroom = classroomRepo.findById(classId).orElseThrow();




        return classSubjectMapper.toResponseList(classroom.getSubjectList());
    }

    @Transactional
    @Override
    public void addMaterial(Long subjectId, AddMaterialDto materialDto, MultipartFile file) throws IOException {
        ClassSubject subject = classSubjectRepo.findById(subjectId).orElseThrow();


        ClassMaterial material = materialMapper.toEntity(materialDto);

        if(file != null){
        material.setFile(fileUploadService.uploadFileToFileSystem(file));
        }

        subject.addMaterial(material);

        classSubjectRepo.save(subject);


    }

    @Override
    public List<ReadMaterialDto> loadSubjectMaterials(Long subjectId) {
        return classMaterialRepo.findClassMaterialBySubjectId(subjectId);
    }

    @Override
    public List<ReadTeacherMaterialDto> loadClassroomMaterials(Long classId) {
        return classMaterialRepo.findClassroomMaterialsByClassroomId(classId);
    }


    @Override
    public TeacherHerderInfoDto loadTeacherClassroomsSummary(String email) {
        return teacherRepo.findTeacherByEmailClassroomDetails(email);
    }

    @Override
    public List<TeacherClassroomResponseDto> loadTeacherClassrooms(String email) {
        return teacherRepo.findByTeacherEmailAllClassrooms(email);
    }

    @Transactional
    @Override
    public void removeMaterial(Long id) {
        classMaterialRepo.deleteById(id);
    }


    @Transactional
    @Override
    public void removeSubject(Long id) {
        classSubjectRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void removeClass(Long id) {
        classroomRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void updateSubject(ReadSubjectDto subjectDto) {
        ClassSubject subject = classSubjectRepo.findById(subjectDto.getId()).orElseThrow();

        subject.setName(subjectDto.getName());


        classSubjectRepo.save(subject);
    }
}

package com.geology_platform.geology.service.classroom;

import com.geology_platform.geology.dto.classroom.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/
public interface ClassroomService {
    void addClassroom(ClassroomDto classroomDto, MultipartFile coverImage) throws IOException;
    void joinClassroom(JoinClassroomDto joinClassroomDto);

    List<ClassroomInfoDto> laodStudentClassrooms(String email);

    void addSubject(Long id,AddSubjectDto subjectDto);

    Set<ReadSubjectDto> loadAllSubjects(Long classId);

    void addMaterial(Long subjectId,AddMaterialDto materialDto,MultipartFile file) throws IOException;

    List<ReadMaterialDto> loadSubjectMaterials(Long subjectId);

    List<ReadTeacherMaterialDto> loadClassroomMaterials(Long classId);

    TeacherHerderInfoDto loadTeacherClassroomsSummary(String email);


    List<TeacherClassroomResponseDto> loadTeacherClassrooms(String email);


    void removeMaterial(Long id);
    void removeSubject(Long id);
    void removeClass(Long id);
    void updateSubject(ReadSubjectDto subjectDto);

}

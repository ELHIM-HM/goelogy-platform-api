package com.geology_platform.geology.service.student;

import com.geology_platform.geology.dto.student.BrancheDto;
import com.geology_platform.geology.dto.student.FormationDto;
import com.geology_platform.geology.entity.student.Student;
import com.geology_platform.geology.projection.StudentProjection;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/
public interface StudentService {
    void addFormation(FormationDto formationDto);

    void addBranche(Long formationId, BrancheDto brancheDto);

    List<FormationDto> loadFormations();

    List<BrancheDto> loadBranches(String formationName);

    void addStudent(String branchName,Student student,String verificationCode);

    Long countStudent();

    void removeStudent(Long id);

    List<StudentProjection> loadAllStudents(Integer page,Integer size,Long brancheId);

    void deleteAllFormation();


}

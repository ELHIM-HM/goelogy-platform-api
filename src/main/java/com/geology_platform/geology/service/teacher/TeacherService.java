package com.geology_platform.geology.service.teacher;

import com.geology_platform.geology.dto.teacher.GradeDto;
import com.geology_platform.geology.entity.teacher.Teacher;
import com.geology_platform.geology.projection.TeacherProjection;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/
public interface TeacherService {
    void addGrade(GradeDto gradeDto);
    List<GradeDto> loadGrades();

    void addTeacher(Long gradeId,Teacher teacher);
    void validateTeacher(Long id,boolean isValidated);
    Long teachersCount();

    List<TeacherProjection> loadTeachers(Integer page, Integer size, Long gradeId);

    String generateValidationCode(Long duration);
}

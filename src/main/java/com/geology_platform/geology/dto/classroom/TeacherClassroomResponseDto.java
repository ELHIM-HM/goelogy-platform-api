package com.geology_platform.geology.dto.classroom;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author ELHIM Hamza
 **/

@Getter
@Setter
public class TeacherClassroomResponseDto {

    private Long id;
    private String imageName;
    private String title;
    private String classCode;
    private Long nbStudents;
    private Long nbSubjects;
    private Long nbMaterials;
    private LocalDate createdAt;

    public TeacherClassroomResponseDto(Long id,String imageName, String title, String classCode, Long nbStudents, Long nbSubjects, Long nbMaterials, LocalDate createdAt) {
        this.id = id;
        this.imageName = imageName;
        this.title = title;
        this.classCode = classCode;
        this.nbStudents = nbStudents;
        this.nbSubjects = nbSubjects;
        this.nbMaterials = nbMaterials;
        this.createdAt = createdAt;
    }
}

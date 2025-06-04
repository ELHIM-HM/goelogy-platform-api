package com.geology_platform.geology.dto.classroom;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ELHIM Hamza
 **/


@Getter
@Setter
public class TeacherHerderInfoDto {

    private Long nbClasses;
    private Long nbStudents;
    private Long nbSubjects;
    private Long nbMaterials;

    public TeacherHerderInfoDto(Long nbClasses, Long nbStudents, Long nbSubjects, Long nbMaterials) {
        this.nbClasses = nbClasses;
        this.nbStudents = nbStudents;
        this.nbSubjects = nbSubjects;
        this.nbMaterials = nbMaterials;
    }
}

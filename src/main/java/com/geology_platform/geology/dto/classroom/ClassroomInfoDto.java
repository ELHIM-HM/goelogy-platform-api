package com.geology_platform.geology.dto.classroom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ELHIM Hamza
 **/


@Getter
@Setter
public class ClassroomInfoDto {

    private Long classroomId;
    private String classroomTitle;
    private String teacherFullName;
    private Integer totalStudents;
    private String coverImage;

    public ClassroomInfoDto(Long classroomId, String classroomTitle, String teacherFullName, Integer totalStudents,String coverImage) {
        this.classroomId = classroomId;
        this.classroomTitle = classroomTitle;
        this.teacherFullName = teacherFullName;
        this.totalStudents = totalStudents;
        this.coverImage = coverImage;
    }
}

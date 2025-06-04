package com.geology_platform.geology.dto.classroom;

import lombok.Data;

/**
 * @author ELHIM Hamza
 **/

@Data
public class StudentClassroomsResponseDto {

    private String title;
    private String teacherFullName;
    private Long studentsCount;
    private Long materialsCount;

}

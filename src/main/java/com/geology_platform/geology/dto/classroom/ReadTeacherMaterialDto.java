package com.geology_platform.geology.dto.classroom;

import java.time.LocalDateTime;

/**
 * @author ELHIM Hamza
 **/


public class ReadTeacherMaterialDto extends ReadMaterialDto {

    private String subjectName;


    public ReadTeacherMaterialDto(Long id, String title, String description, String type, LocalDateTime createdAt, String fileName, String subjectName) {
        super(id, title, description, type, createdAt, fileName);
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}

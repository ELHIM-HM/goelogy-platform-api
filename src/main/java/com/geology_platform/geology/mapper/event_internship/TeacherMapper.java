package com.geology_platform.geology.mapper.event_internship;

import com.geology_platform.geology.dto.both.TeacherDTO;
import com.geology_platform.geology.entity.teacher.Teacher;

public class TeacherMapper {
    public static TeacherDTO mapToTeacherDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        dto.setPhoneNumber(teacher.getPhoneNumber());
        dto.setIdentityCardNumber(teacher.getIdentityCardNumber());
        dto.setBirthDay(teacher.getBirthDay());
        dto.setValidated(teacher.isValidated());

        return dto;
    }

}

package com.geology_platform.geology.dto.both;

import lombok.Data;

@Data
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String identityCardNumber;
    private String birthDay;
    private boolean validated;
}

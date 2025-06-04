package com.geology_platform.geology.service;

import com.geology_platform.geology.repository.classroom.ClassroomRepo;
import com.geology_platform.geology.repository.teacher.CodeGenerationRepo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 * @author ELHIM Hamza
 **/

@Service
@AllArgsConstructor
public class CodeGeneratorService {

    private ClassroomRepo classroomRepo;

    public String generateUniqueJoinCode(){
        String joinCode;

        do {
            joinCode = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
        }while (classroomRepo.existsByJoinCode(joinCode));


        return joinCode;

    }

}

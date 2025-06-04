package com.geology_platform.geology.exception.student;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class StudentAlreadyExist extends BaseBusinessException {
    public StudentAlreadyExist(Object details) {
        super("Un(e) autre étudiant(e) est déjà inscrit avec cet e-mail.", "EXISTS_STUDENT", details, HttpStatus.CONFLICT);
    }
}

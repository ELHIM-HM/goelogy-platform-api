package com.geology_platform.geology.exception.teacher;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class TeacherAlreadyExists extends BaseBusinessException {

    public TeacherAlreadyExists(Object details) {
        super("Teacher already exists", "EXISTS_TEACHER", details, HttpStatus.CONFLICT);
    }
}

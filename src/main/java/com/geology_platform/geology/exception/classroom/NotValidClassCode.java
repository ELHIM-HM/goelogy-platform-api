package com.geology_platform.geology.exception.classroom;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class NotValidClassCode extends BaseBusinessException {
    public NotValidClassCode(Object details) {
        super("Le code que vous avez entré n'est pas valide.", "NOT_VALID_CLASS_CODE", details, HttpStatus.NOT_ACCEPTABLE);
    }
}

package com.geology_platform.geology.exception.teacher;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/

public class TeacherAccountNotValidated extends BaseBusinessException {

    public TeacherAccountNotValidated(Object details) {
        super("Votre compte n'est pas encore activé. Vous recevrez un e-mail dès qu'il sera activé.", "NOT_VALIDATED_ACCOUNT", details, HttpStatus.FORBIDDEN);
    }
}

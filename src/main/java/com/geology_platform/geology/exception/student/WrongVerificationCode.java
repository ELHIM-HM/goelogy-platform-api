package com.geology_platform.geology.exception.student;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class WrongVerificationCode extends BaseBusinessException {
    public WrongVerificationCode(Object details) {
        super("Le code de vérification n'est pas valide", "NOT_VALID_CODE" , details, HttpStatus.NOT_ACCEPTABLE);
    }
}

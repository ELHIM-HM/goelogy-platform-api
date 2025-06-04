package com.geology_platform.geology.exception.jwt;

import lombok.Getter;

/**
 * @author ELHIM Hamza
 **/
public class JwtBaseException extends RuntimeException {
    @Getter
    private String errorCode;
    public JwtBaseException(String message ,String errorCode1) {
        super(message);
        this.errorCode = errorCode1;
    }
}
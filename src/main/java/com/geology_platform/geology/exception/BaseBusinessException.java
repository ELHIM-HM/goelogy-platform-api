package com.geology_platform.geology.exception;

import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public abstract class BaseBusinessException extends RuntimeException {
    private final String errorCode;
    private final Object details;
    private final HttpStatus status;


    public BaseBusinessException(String message, String errorCode, Object details, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
        this.status = status;
    }

    public String getErrorCode() { return errorCode; }
    public Object getDetails() { return details; }

    public HttpStatus getStatus() {
        return status;
    }
}

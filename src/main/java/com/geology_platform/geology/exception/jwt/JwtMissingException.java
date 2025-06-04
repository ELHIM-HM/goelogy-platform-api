package com.geology_platform.geology.exception.jwt;

/**
 * @author ELHIM Hamza
 **/
public class JwtMissingException extends JwtBaseException {
    public JwtMissingException() {
        super("JWT token is missing from the request","MISSING_TOKEN");
    }
}


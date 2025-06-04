package com.geology_platform.geology.exception.jwt;

/**
 * @author ELHIM Hamza
 **/
public class JwtInvalideException extends JwtBaseException {
    public JwtInvalideException() {
        super("Invalid JWT token format or signature","INVALID_TOKEN");
    }
}

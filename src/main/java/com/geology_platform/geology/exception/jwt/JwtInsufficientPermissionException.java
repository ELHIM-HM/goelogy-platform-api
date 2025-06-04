package com.geology_platform.geology.exception.jwt;

/**
 * @author ELHIM Hamza
 **/
public class JwtInsufficientPermissionException extends JwtBaseException {
    public JwtInsufficientPermissionException() {
        super("Insufficient permissions to access this resource", "INSUFFICIENT_PERMISSION");
    }
}

package com.geology_platform.geology.exception.jwt;

/**
 * @author ELHIM Hamza
 **/
public class JwtExpiredException extends JwtBaseException {
  public JwtExpiredException() {
    super("JWT token has expired","EXPIRED_TOKEN");
  }}

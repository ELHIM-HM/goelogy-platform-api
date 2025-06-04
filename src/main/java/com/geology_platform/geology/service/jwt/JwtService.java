package com.geology_platform.geology.service.jwt;

import java.util.Map;

/**
 * @author ELHIM Hamza
 **/
public interface JwtService {

    String generateAccessToken(String username,String issuer); // Principal contains the currently authenticated user ( usually contains one method getUsername() )
    String generateRefreshToken(String username,String issuer);

    Map<String,String> getGeneratedTokensMap(String username, String issuer);

}

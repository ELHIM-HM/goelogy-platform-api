package com.geology_platform.geology.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.geology_platform.geology.entity.teacher.Teacher;
import com.geology_platform.geology.entity.user.Users;
import com.geology_platform.geology.exception.jwt.JwtExpiredException;
import com.geology_platform.geology.exception.jwt.JwtInvalideException;
import com.geology_platform.geology.exception.jwt.JwtMissingException;
import com.geology_platform.geology.exception.teacher.TeacherAccountNotValidated;
import com.geology_platform.geology.repository.teacher.TeacherRepo;
import com.geology_platform.geology.service.admine.AdmineServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ELHIM Hamza
 **/

@Service
public class JwtServiceImpl implements JwtService {

    private AdmineServiceImpl admineService;
    private TeacherRepo teacherRepo;

    @Value("${spring.jwt.secret}")
    private  String SECRET_KEY;

    @Value("${spring.jwt.access-token-expiration}")
    private  Long ACCESS_TOKEN_EXPIRATION;

    @Value("${spring.jwt.refresh-token-expiration}")
    private  Long REFRESH_TOKEN_EXPIRATION;

    @Getter
    private final String JWT_PREFEX = "Bearer ";
    @Getter
    private final String AUTH_HEADER = "Authorization";
    @Getter
    private final String REFRESH_TOKEN_URL = "/refreshToken";


    public JwtServiceImpl(AdmineServiceImpl admineService, TeacherRepo teacherRepo) {
        this.admineService = admineService;
        this.teacherRepo = teacherRepo;
    }

    @Override
    public String generateAccessToken(String username, String issuer) {
        Users currentAuthenticatedUser = admineService.loadUserByUsername(username);

        System.out.println("authorities list for user : ");
        System.out.println(getAuthoritiesList(currentAuthenticatedUser));

        return  JWT
                .create()
                .withSubject(currentAuthenticatedUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION ))
                .withIssuedAt(new Date())
                .withIssuer(issuer)// name of the app that generate the token
                .withClaim("roles",getAuthoritiesList(currentAuthenticatedUser))
                .sign(getSigningAlgorithm());
    }

    @Override
    public String generateRefreshToken(String username, String issuer) {
        Users currentAuthenticatedUser = admineService.loadUserByUsername(username);

        return JWT
                .create()
                .withSubject(currentAuthenticatedUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION ))
                .withIssuedAt(new Date())
                .withIssuer(issuer)// name of the app that generate the token
                .sign(getSigningAlgorithm());
    }

    @Override
    public Map<String, String> getGeneratedTokensMap(String username, String issuer) {
        Map<String ,String> tokens = new HashMap<>();

        tokens.put("accessToken",generateAccessToken(username,issuer));
        tokens.put("refreshToken",generateRefreshToken(username,issuer));

        return tokens;
    }

    public DecodedJWT validateToken(String token){

        if(token == null || token.isEmpty()){
            throw  new JwtMissingException();
        }

        try {
            JWTVerifier verifier = JWT.require(getSigningAlgorithm()).build();
            return verifier.verify(token);
        }catch (TokenExpiredException e){
            throw new JwtExpiredException();
        }
        catch (JWTVerificationException e){
            throw new JwtInvalideException();
        }


    }


    @Transactional(readOnly = true)
    public void verifyTeacherValidationAccount(String email){
        Teacher teacher =  teacherRepo.findByEmail(email);


        if(teacher != null){
            if(!teacher.isValidated()){
                throw new TeacherAccountNotValidated(email);
            }
        }

    }


    public Collection<GrantedAuthority> getAuthoritiesFromToken(String token){
        DecodedJWT jwt = validateToken(token);

        return jwt.getClaim("roles").asList(String.class).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


    public Algorithm getSigningAlgorithm(){
        return Algorithm.HMAC256(SECRET_KEY);
    }

    private List<String> getAuthoritiesList(Users user){

        System.out.println("user : ");
        System.out.println(user);

        System.out.println("user authorities ");
        System.out.println(user.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList()));

        return user.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());
    }
}

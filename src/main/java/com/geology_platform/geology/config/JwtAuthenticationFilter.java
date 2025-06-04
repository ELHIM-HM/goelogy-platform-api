package com.geology_platform.geology.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geology_platform.geology.exception.jwt.ApiError;
import com.geology_platform.geology.exception.teacher.TeacherAccountNotValidated;
import com.geology_platform.geology.repository.teacher.TeacherRepo;
import com.geology_platform.geology.service.jwt.JwtService;
import com.geology_platform.geology.service.jwt.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

/**
 * @author ELHIM Hamza
 **/
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;
    private JwtServiceImpl jwtService;




    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtServiceImpl jwtService) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        setFilterProcessesUrl("/login");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        try {
            System.out.println("attempt authentication");

            String username = request.getParameter("email");
            String password = request.getParameter("password");

            System.out.println("request : ");
            System.out.println(request);

            System.out.println("email "+username);
            System.out.println("password "+password);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

            return authenticationManager.authenticate(authenticationToken);

        }catch (AuthenticationException e){
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            HttpStatus status  = HttpStatus.UNAUTHORIZED;

            response.setStatus(status.value());

            ApiError error = ApiError.of(
                    status.value(),
                    status.getReasonPhrase(),
                    "username or password are invalid",
                    request.getRequestURI(),
                    "INVALID_CREDENTIALS"
            );

            try {
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {


        try {

            System.out.println("successful authentication");

            System.out.println("authResult : "+authResult.getName());

            jwtService.verifyTeacherValidationAccount(authResult.getName());


            SecurityContextHolder.getContext().setAuthentication(authResult);

            Map<String,String> generatedTokens = jwtService.getGeneratedTokensMap(authResult.getName(),request.getRequestURI());


            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            // using jackson to send the tokens inside the answer response body
            new ObjectMapper().writeValue(response.getOutputStream(),generatedTokens);
        }catch (TeacherAccountNotValidated e){

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            HttpStatus status  = HttpStatus.FORBIDDEN;

            response.setStatus(status.value());

            ApiError error = ApiError.of(
                    status.value(),
                    status.getReasonPhrase(),
                    e.getMessage(),
                    request.getRequestURI(),
                    e.getErrorCode()
            );

            try {
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }


    }



}

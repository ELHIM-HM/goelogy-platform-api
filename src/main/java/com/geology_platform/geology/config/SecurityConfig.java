package com.geology_platform.geology.config;

import com.geology_platform.geology.service.admine.AdmineServiceImpl;
import com.geology_platform.geology.service.jwt.JwtServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**

@author ELHIM Hamza
    
   **/

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true, // Allows using @PreAuthorize and @PostAuthorize
        securedEnabled = true //  Allows using @Secured("ROLE_ADMIN") This is older, and only checks simple roles.
)
public class SecurityConfig {

    private AdmineServiceImpl admineService;
    private JwtServiceImpl jwtService;

    public SecurityConfig(AdmineServiceImpl admineService, JwtServiceImpl jwtService) {
        this.admineService = admineService;
        this.jwtService = jwtService;
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://localhost:5173",
                "http://127.0.0.1:5173",
                "http://localhost:5174",
                "http://localhost:5175",
                "http://192.168.112.66:5173",
                "http://10.220.0.79:5173"
        ));

        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;

    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> admineService.loadUserByUsername(username);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder =  http.getSharedObject(AuthenticationManagerBuilder.class);
        return builder.build();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationManager authenticationManager) throws  Exception {
        http.cors(cors->cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf->csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // for non stateful servers
                .authorizeHttpRequests(
                        req->req.requestMatchers("/**")
                                .permitAll()
                                .anyRequest().authenticated()

                );

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager,jwtService);

        http.addFilter(jwtAuthenticationFilter);

        return http.build();

    }





}

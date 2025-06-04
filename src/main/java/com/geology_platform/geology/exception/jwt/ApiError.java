package com.geology_platform.geology.exception.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ELHIM Hamza
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private int status;
    private String error;
    private String message;
    private String code;
    private String path;

    public static ApiError of(int status, String error, String message, String path, String code) {
        return ApiError.builder()
                .status(status)
                .error(error)
                .message(message)
                .path(path)
                .code(code)
                .build();
    }

}

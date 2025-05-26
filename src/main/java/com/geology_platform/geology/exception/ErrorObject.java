package com.geology_platform.geology.exception;

import lombok.Data;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
}

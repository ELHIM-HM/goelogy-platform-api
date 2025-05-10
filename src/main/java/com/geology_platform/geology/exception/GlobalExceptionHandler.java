package com.geology_platform.geology.exception;


import com.geology_platform.geology.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ELHIM Hamza
 **/

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc){
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "BAD_REQUEST",
                "Bad Request",
                System.currentTimeMillis(),
                ""
        );

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BaseBusinessException exc){
        ErrorResponse error = new ErrorResponse(
                exc.getStatus().value(),
                exc.getErrorCode(),
                exc.getMessage(),
                System.currentTimeMillis(),
                exc.getDetails()
        );



        return new ResponseEntity<>(error,exc.getStatus());

    }




}

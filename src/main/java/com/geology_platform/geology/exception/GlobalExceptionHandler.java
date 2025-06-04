package com.geology_platform.geology.exception;


import com.geology_platform.geology.dto.response.ErrorResponse;
import com.geology_platform.geology.exception.jwt.ApiError;
import com.geology_platform.geology.exception.jwt.JwtBaseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
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

    @ExceptionHandler(JwtBaseException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtBaseException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ApiError error = ApiError.of(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI(),
                ex.getErrorCode()
        );

        return new ResponseEntity<>(error, status);
    }


    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiError> handleGenericException(AuthorizationDeniedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        ApiError error = ApiError.of(
                status.value(),
                status.getReasonPhrase(),
                "You do not have permission",
                request.getRequestURI(),
                "ACCESS_DENIED"
        );

        return new ResponseEntity<>(error, status);
    }




}

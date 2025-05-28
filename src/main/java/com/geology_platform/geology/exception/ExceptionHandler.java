package com.geology_platform.geology.exception;

import com.geology_platform.geology.exception.event_internship.*;
import com.geology_platform.geology.exception.teacher.TeacherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(
            {
                    InternshipNotFoundException.class,
                    InternshipCategoryNotFoundException.class,
                    EventCategoryNotFoundException.class,
                    EventNotFoundException.class,
                    SectorNotFoundException.class,
                    JobNotFoundException.class,
                    ThesisNotFoundException.class,
                    TeacherNotFoundException.class
            }
            )

    public ResponseEntity<ErrorObject> handleNotFoundExceptions(RuntimeException ex){
        ErrorObject error = new ErrorObject();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }


    @org.springframework.web.bind.annotation.ExceptionHandler(
            {
            InternshipCategoryExistsAlready.class,
            EventCategoryExistsAlready.class,
            SectorExistsAlready.class
            }

    )

    public ResponseEntity<ErrorObject> handleExistsAlreadyExceptions(
            RuntimeException ex
    ){
        ErrorObject error = new ErrorObject();
        error.setStatusCode(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(
            {
                    InvalidLevelException.class,
                    InvalidThesisStatusException.class
            }
    )
    public ResponseEntity<ErrorObject> handleInvalidExceptions(RuntimeException ex) {
        ErrorObject error = new ErrorObject();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}

package com.geology_platform.geology.exception.classroom;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/


public class StudentAlreadyJoined extends BaseBusinessException {
  public StudentAlreadyJoined(Object details) {
    super("Vous avez déjà rejoint la classe.", "NOT_VALID_CLASS_CODE", details, HttpStatus.NOT_ACCEPTABLE);
  }
}

package com.geology_platform.geology.exception.global;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class ResourceNotFound extends BaseBusinessException {
  public ResourceNotFound(String message,Object details) {
    super(message, "NOT_FOUND", details, HttpStatus.NOT_FOUND);
  }
}

package com.geology_platform.geology.exception.collection;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class CategoryNotFound extends BaseBusinessException {
  public CategoryNotFound(Object details) {
    super("Category Not Found", "CATEGORY_NOT_FOUND" , details, HttpStatus.NOT_FOUND);
  }
}

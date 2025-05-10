package com.geology_platform.geology.exception.collection;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class SubCategoryNotFound extends BaseBusinessException {
  public SubCategoryNotFound(Object details) {
    super("SubCategory Not Found", "SUB_CATEGORY_NOT_FOUND" , details, HttpStatus.NOT_FOUND);
  }
}

package com.geology_platform.geology.exception.ibrary;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;



/**

@author ELHIM Hamza
    
   **/

public class ArticleNotFound extends BaseBusinessException {

  public ArticleNotFound(Object details) {
    super("Article Not found Exist", "NOT_FOUND_ARTICLE" , details, HttpStatus.NOT_FOUND);
  }
}




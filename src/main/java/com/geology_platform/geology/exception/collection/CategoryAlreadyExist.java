package com.geology_platform.geology.exception.collection;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class CategoryAlreadyExist extends BaseBusinessException {

    public CategoryAlreadyExist(Object details) {
        super("Category Already Exist", "EXISTS_CATEGORY" , details, HttpStatus.CONFLICT);
    }
}

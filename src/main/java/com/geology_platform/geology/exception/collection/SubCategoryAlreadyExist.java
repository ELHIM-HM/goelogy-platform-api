package com.geology_platform.geology.exception.collection;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class SubCategoryAlreadyExist extends BaseBusinessException {
    public SubCategoryAlreadyExist(Object details) {
        super("SubCategory Already Exist", "EXISTS_SUB_CATEGORY" , details, HttpStatus.CONFLICT);
    }
}

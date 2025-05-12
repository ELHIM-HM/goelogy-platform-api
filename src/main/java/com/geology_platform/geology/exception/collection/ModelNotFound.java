package com.geology_platform.geology.exception.collection;

import com.geology_platform.geology.exception.BaseBusinessException;
import org.springframework.http.HttpStatus;

/**
 * @author ELHIM Hamza
 **/
public class ModelNotFound extends BaseBusinessException {
    public ModelNotFound(Object details) {
        super("Model Not Found", "MODEL_NOT_FOUND" , details, HttpStatus.NOT_FOUND);
    }
}

package com.geology_platform.geology.dto.request.library;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ELHIM Hamza
 **/

@Data
@AllArgsConstructor
public class RequestUpdateArticle {
    private String title;


    private String descreption;
}

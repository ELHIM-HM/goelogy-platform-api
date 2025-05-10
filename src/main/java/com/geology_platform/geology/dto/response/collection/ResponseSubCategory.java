package com.geology_platform.geology.dto.response.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ELHIM Hamza
 **/


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSubCategory {

    private Long subCategoryId;


    private String name;


    private String description;
}

package com.geology_platform.geology.dto.request.collection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RequestSubCategory {

    @NotBlank
    @NotNull
    private String name;

    private String description;
}
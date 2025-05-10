package com.geology_platform.geology.dto.request.collection;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ELHIM Hamza
 **/

@Getter
@Setter
@AllArgsConstructor
public class RequestCategory {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String key;

    private String description;

}

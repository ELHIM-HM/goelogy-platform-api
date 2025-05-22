package com.geology_platform.geology.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ELHIM Hamza
 **/

@Data
public class FormationDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;
}

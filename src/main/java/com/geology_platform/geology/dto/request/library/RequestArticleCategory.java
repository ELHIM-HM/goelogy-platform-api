package com.geology_platform.geology.dto.request.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ELHIM Hamza
 **/

@Setter
@Getter
public class RequestArticleCategory {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;


    @NotNull
    private String name;

}

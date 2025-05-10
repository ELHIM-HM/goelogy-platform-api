package com.geology_platform.geology.dto.response.collection;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class ResponseCategory {


    private Long categoryId;

    private String name;

    private String description;

    private String key;
}

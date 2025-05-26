package com.geology_platform.geology.dto.both;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternshipCategoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String label;
}

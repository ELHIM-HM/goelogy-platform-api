package com.geology_platform.geology.dto.both;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ActivitySectorDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String label;
}

package com.geology_platform.geology.dto.both;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ThesisRequestDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String subject;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createdAt ;
    private LocalDate startDate;
    private LocalDate endDate;
    private String level;
    private String status;
    private List<Long> supervisorsIds;
}

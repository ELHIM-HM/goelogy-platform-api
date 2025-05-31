package com.geology_platform.geology.dto.both;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.FileData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor @AllArgsConstructor
public class EventDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String location;
    private LocalDate date;
    private String title;
    private String description;
    private String summary;
    private int categoryId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private FileData img;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private String categoryLabel;
}

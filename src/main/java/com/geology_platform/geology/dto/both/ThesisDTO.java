package com.geology_platform.geology.dto.both;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.ThesisStatus;
import com.geology_platform.geology.entity.teacher.Teacher;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ThesisDTO {
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

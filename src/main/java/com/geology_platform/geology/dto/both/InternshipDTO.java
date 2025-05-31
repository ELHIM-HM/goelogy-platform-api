package com.geology_platform.geology.dto.both;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class InternshipDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String title;
    private String description;
    private double remuneration;
    private LocalDate startDate;
    private LocalDate endDate;
    private String recruiter;
    private String recruiterPhoneNumber;
    private String recruiterEmail;
    private String country;
    private String city;
    private boolean isRemote;
    private long categoryId;
    private long sectorId;
}

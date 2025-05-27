package com.geology_platform.geology.dto.both;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Data
@Getter
public class JobDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String title;
    private String description;
    private String recruiter;
    private String recruiterPhoneNumber;
    private String recruiterEmail;
    private String country;
    private String city;
    private String contractType;
    private int requiredExperienceDurationInMonths;
    @NotBlank
    private int categoryId;
    @NotBlank
    private int sectorId;
    private boolean remote;
}

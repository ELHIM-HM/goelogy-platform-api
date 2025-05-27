package com.geology_platform.geology.entity.event_internship;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String recruiter;
    @Pattern(regexp = "^(\\+\\d{1,3})?\\d{8,15}$")
    private String recruiterPhoneNumber;
    @Email(message = "Invalid Email")
    private String recruiterEmail;
    private String country;
    private String city;
    private String contractType;
    private int requiredExperienceDuration;
    @ManyToOne
    private ActivitySector sector;
    @ManyToOne
    private InternshipCategory category;
    private boolean remote;



}

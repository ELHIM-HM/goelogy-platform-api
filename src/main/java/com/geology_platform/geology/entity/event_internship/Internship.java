package com.geology_platform.geology.entity.event_internship;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Internship {
    //add Entreprise and remote and country and city
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private double remuneration;
    private LocalDate startDate;
    private LocalDate endDate;
    private String recruiter;
    @Pattern(regexp = "^(\\+\\d{1,3})?\\d{8,15}$")
    private String recruiterPhoneNumber;
    @Email(message = "Invalid Email")
    private String recruiterEmail;
    private String country;
    private String city;
    private boolean isRemote;
    @ManyToOne
    private InternshipCategory category;
    @ManyToOne
    private ActivitySector sector;
}

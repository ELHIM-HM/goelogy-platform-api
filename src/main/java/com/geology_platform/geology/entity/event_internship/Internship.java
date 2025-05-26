package com.geology_platform.geology.entity.event_internship;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private double remuneration;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    private InternshipCategory category;

    @ManyToOne
    private ActivitySector sector;
}

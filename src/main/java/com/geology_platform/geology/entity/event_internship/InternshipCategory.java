package com.geology_platform.geology.entity.event_internship;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class InternshipCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;

    @OneToMany(mappedBy = "category")
    private Collection<Internship> internships;
}

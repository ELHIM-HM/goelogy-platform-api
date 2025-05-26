package com.geology_platform.geology.entity.event_internship;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String location;
    private LocalDate date;
    private String title;
    private String description;
    private String summary;
    @ManyToOne
    private EventCategory category;
}

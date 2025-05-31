package com.geology_platform.geology.entity.event_internship;

import com.geology_platform.geology.entity.FileData;
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
    @OneToOne(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    @JoinColumn(name = "img_id")
    private FileData img;
    @ManyToOne
    private EventCategory category;
}

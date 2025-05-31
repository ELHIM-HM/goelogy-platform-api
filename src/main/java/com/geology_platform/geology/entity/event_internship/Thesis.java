package com.geology_platform.geology.entity.event_internship;

import com.geology_platform.geology.entity.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor  @NoArgsConstructor
public class Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private String description;
    @CreationTimestamp
    private LocalDate createdAt ;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private ThesisStatus status;
    @ManyToMany
    @JoinTable(
            name = "thesis_supervisor",
            joinColumns = @JoinColumn(name = "thesis_id"),
            inverseJoinColumns = @JoinColumn(name = "supervisor_id")
    )
    private List<Teacher> supervisors;

}

package com.geology_platform.geology.entity.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.Person;
import com.geology_platform.geology.entity.classroom.Classroom;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "student")
public class Student extends Person {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer anneeScolaire;

    private Integer semisterNumber;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branche_id")
    private Branche branche;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_classroom",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private List<Classroom> classroomList;

}

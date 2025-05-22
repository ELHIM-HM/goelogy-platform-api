package com.geology_platform.geology.entity.student;

import com.geology_platform.geology.entity.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ELHIM Hamza
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "student")
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer anneeScolaire;

    private Integer semisterNumber;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branche_id")
    private Branche branche;

}

package com.geology_platform.geology.entity.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "teacher")
public class Teacher extends Person {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "validated",nullable = false)
    private boolean validated = false;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;



}

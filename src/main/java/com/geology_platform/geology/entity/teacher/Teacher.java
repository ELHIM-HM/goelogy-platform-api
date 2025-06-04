package com.geology_platform.geology.entity.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.Person;
import com.geology_platform.geology.entity.classroom.Classroom;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Classroom> classroomList = new ArrayList<>();

    public void addClassroom(Classroom classroom){
        classroomList.add(classroom);
        classroom.setTeacher(this);
    }

    public void removeClassroom(Classroom classroom){
        classroomList.remove(classroom);
        classroom.setTitle(null);
    }



}

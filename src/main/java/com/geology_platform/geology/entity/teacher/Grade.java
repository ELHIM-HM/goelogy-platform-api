package com.geology_platform.geology.entity.teacher;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Entity
@Table(name = "grade")
@Data
public class Grade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "grade",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    private List<Teacher> teachers = new ArrayList<>();

    public void addTeacher(Teacher teacher){
        if(teachers == null){
            teachers = new ArrayList<>();
        }
        teachers.add(teacher);
        teacher.setGrade(this);
    }


}

package com.geology_platform.geology.entity.student;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Data
@Entity
@Table(name = "branche")
public class Branche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "branche",fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<Student> students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "formation_id")
    private Formation formation;


    public void addStudent(Student student){
        students.add(student);
        student.setBranche(this);
    }



}
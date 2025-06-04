package com.geology_platform.geology.entity.classroom;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "subject")
    private Set<ClassMaterial> materialList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;


    public  void addMaterial(ClassMaterial material){

        if (materialList == null) {
            materialList = new HashSet<>();
        }

        materialList.add(material);
        material.setSubject(this);
    }

}

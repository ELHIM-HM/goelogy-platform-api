package com.geology_platform.geology.entity.student;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Data
@Entity
@Table(name = "formation")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @OneToMany(mappedBy = "formation",fetch = FetchType.LAZY)
    private List<Branche> brancheList = new ArrayList<>();

    public void addBranche(Branche branche){
        if(brancheList == null){
            brancheList = new ArrayList<>();
        }

        brancheList.add(branche);
        branche.setFormation(this);
    }

}

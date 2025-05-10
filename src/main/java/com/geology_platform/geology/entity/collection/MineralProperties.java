package com.geology_platform.geology.entity.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author ELHIM Hamza
 **/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mineral_properties")
public class MineralProperties {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mineralId")
    private Long mineralId;

    @JsonIgnore
    @Column(name = "model_id", unique = true) // This is important
    private Long modelId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "chemical_formula")
    private String chemicalFormula;

    @Column(name = "class")
    private String mineralClass;

    @Column(name = "crystal_system")
    private String crystalSystem;

    @Column(name = "color")
    private String color;

    @Column(name = "eclat")
    private String eclat;

    @Column(name = "durete")
    private BigDecimal durete;

    @Column(name = "clivage")
    private String clivage;

    @Column(name = "cassure")
    private String cassure;

    @Column(name = "density")
    private BigDecimal density;

    @Column(name = "transparency")
    private String transparency;

    @Column(name = "uses")
    private String uses;
}

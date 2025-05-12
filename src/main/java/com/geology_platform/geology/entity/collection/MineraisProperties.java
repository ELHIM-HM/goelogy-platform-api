package com.geology_platform.geology.entity.collection;

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
@Table(name = "minerais_properties")
public class MineraisProperties {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mineraislId")
    private Long mineralId;




    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "extracted_elements", nullable = false)
    private String extracted_elements;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "uses")
    private String uses;
}


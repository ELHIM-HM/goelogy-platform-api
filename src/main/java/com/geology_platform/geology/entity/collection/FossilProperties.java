package com.geology_platform.geology.entity.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ELHIM Hamza
 **/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fossil_properties")
public class FossilProperties {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fossilId")
    private Long fossilId;




    @Column(name = "scientific_name")
    private String scientificName;

    @Column(name = "age")
    private String age;


    @Column(name = "discovery_location")
    private String discoveryLocation;

    @Column(name = "gps_coordinates")
    private String gpsCoordinates;

    @Column(name = "fossil_type")
    private String fossilType;

    @Column(name = "specimen_type")
    private String specimenType;


}

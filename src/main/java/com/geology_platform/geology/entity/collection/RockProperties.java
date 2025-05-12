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
@Table(name = "rock_properties")
public class RockProperties {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rockId")
    private Long rockId;




    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rock_type")
    private String rockType;

    @Column(name = "color")
    private String color;

    @Column(name = "texture")
    private String texture;

    @Column(name = "primary_minerals")
    private String primaryMinerals;

    @Column(name = "uses")
    private String uses;

    @Column(name = "parent_rock")
    private String parentRock;

    @Column(name = "metamorphism_type")
    private String metamorphismType;
}

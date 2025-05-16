package com.geology_platform.geology.entity.library;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "article")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;


    @Column(name = "descreption")
    private String descreption;


    @Column(name = "cover_image_url")
    private String coverImage;

    @Column(name = "data_url")
    private String dataUrl;


}

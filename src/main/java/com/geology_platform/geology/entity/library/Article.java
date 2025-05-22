package com.geology_platform.geology.entity.library;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.FileData;
import com.geology_platform.geology.entity.collection.Category;
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

    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ArticleCategory category;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_image_id")
    private FileData coverImage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "data_id")
    private FileData fileData;


}

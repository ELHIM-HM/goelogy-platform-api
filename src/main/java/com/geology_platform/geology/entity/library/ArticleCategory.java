package com.geology_platform.geology.entity.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article_category")
@Entity
public class ArticleCategory {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;


@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "category_id")
    private List<Article> articles = new ArrayList<>();

    public void addArticle(Article article){
        if(!articles.contains(article)){
            articles.add(article);
        }
    }

    public void removeArticle(Article article){
        articles.remove(article);
    }

}

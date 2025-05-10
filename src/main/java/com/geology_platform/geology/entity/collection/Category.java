package com.geology_platform.geology.entity.collection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "keyName",nullable = false)
    private String key;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Set<SubCategory> subCategories = new HashSet<>();


    public void addSubCategory(SubCategory subCategory){
        subCategories.add(subCategory);
    }

    public void removeSubCategory(SubCategory subCategory) {
        subCategories.remove(subCategory);
    }

}

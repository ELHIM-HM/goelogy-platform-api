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
@Table(name = "subcategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private Long subCategoryId;


    @Column(name = "name" ,nullable = false )
    private String name;

    @Column(name = "description" )
    private String description;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private Set<ModelItem> modelItems = new HashSet<>();

    public void addModelItem(ModelItem modelItem) {
        modelItems.add(modelItem);
    }

    public void removeModelItem(ModelItem modelItem) {
        modelItems.remove(modelItem);
    }

}

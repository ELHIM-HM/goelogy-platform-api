package com.geology_platform.geology.entity.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.processing.Exclude;

/**
 * @author ELHIM Hamza
 **/

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OtherInfos")
public class OtherInfos {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "property_name", nullable = false)
    private String propertyName;

    @Column(name = "property_value")
    private String propertyValue;


    public OtherInfos(String propertyName, String propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }
}

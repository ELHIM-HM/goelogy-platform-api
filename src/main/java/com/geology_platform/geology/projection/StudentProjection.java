package com.geology_platform.geology.projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.user.Authority;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/
public interface StudentProjection {

    Long getId();

    String getFirstName();
    String getLastName();



    String getBirthDay();
    String getIdentityCardNumber();
    String getPhoneNumber();
    String getEmail();

    Integer getSemisterNumber();

    Integer getAnneeScolaire();

    Long getCreatedAt();


    BrancheInfo getBranche();

    interface BrancheInfo {
        String getName();

        FormationInfo getFormation();

        interface FormationInfo {
            String getName();
        }

    }



}

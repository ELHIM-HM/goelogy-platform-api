package com.geology_platform.geology.projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

/**
 * @author ELHIM Hamza
 **/
public interface TeacherProjection {
     Long getId();
     boolean getValidated();
     String getFirstName();
     String getLastName();
     String getBirthDay();
     String getIdentityCardNumber();
     String getPhoneNumber();
     String getEmail();
     Long getCreatedAt();

     GradeInfo getGrade();

     interface GradeInfo {
         String getName();
     }
}

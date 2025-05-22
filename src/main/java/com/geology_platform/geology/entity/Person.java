package com.geology_platform.geology.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @author ELHIM Hamza
 **/


@Data
@MappedSuperclass
public class Person {
    protected String firstName;
    protected String lastName;
    protected String birthDay;
    protected String identityCardNumber;
    protected String phoneNumber;
    protected String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreationTimestamp
    protected Long createdAt;
}

package com.geology_platform.geology.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.user.Users;
import jakarta.persistence.*;
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

    @Transient
    protected String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreationTimestamp
    protected Long createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id")
    protected Users userInfo;

}

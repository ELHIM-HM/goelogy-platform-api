package com.geology_platform.geology.entity.user;

/**
 * @author ELHIM Hamza
 **/


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.HashSet;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/

@Data
@Entity
public class Users implements UserDetails {



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities = new HashSet<>();


    public void addAuhority(Authority authority){
        authorities.add(authority);
    }

    public void removeAuthority(Authority authority){
        authorities.remove(authority);
    }

}

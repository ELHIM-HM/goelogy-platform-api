package com.geology_platform.geology.service.admine;

import com.geology_platform.geology.entity.user.Authority;
import com.geology_platform.geology.entity.user.Users;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/
public interface AdmineService {
    void addAuthority(Authority authority);

    void removeAuthority(Long authId);

    List<Authority> loadAllAuthorities();

    Users loadUserByUsername(String username);
}

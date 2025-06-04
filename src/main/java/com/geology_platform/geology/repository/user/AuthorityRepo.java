package com.geology_platform.geology.repository.user;

import com.geology_platform.geology.entity.user.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface AuthorityRepo extends JpaRepository<Authority,Long> {
    Authority getAuthoritiesByAuthority(String teacher);
}

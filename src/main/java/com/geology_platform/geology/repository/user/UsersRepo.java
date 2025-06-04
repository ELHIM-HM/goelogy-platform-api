package com.geology_platform.geology.repository.user;

import com.geology_platform.geology.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {
    Users findUsersByUsername(String username);

    void removeUsersById(Long id);
}

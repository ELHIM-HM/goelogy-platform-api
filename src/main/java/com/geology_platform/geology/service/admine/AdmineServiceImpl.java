package com.geology_platform.geology.service.admine;

import com.geology_platform.geology.entity.user.Authority;
import com.geology_platform.geology.entity.user.Users;
import com.geology_platform.geology.repository.user.AuthorityRepo;
import com.geology_platform.geology.repository.user.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Service
@AllArgsConstructor
public class AdmineServiceImpl implements AdmineService {

    private AuthorityRepo authorityRepo;
    private UsersRepo usersRepo;

    @Transactional
    @Override
    public void addAuthority(Authority authority) {
        authorityRepo.save(authority);
    }

    @Transactional
    @Override
    public void removeAuthority(Long authId) {

        Authority authority = authorityRepo.findById(authId).orElseThrow();

        authorityRepo.delete(authority);
    }

    @Override
    public List<Authority> loadAllAuthorities() {
        return authorityRepo.findAll();
    }

    @Override
    public Users loadUserByUsername(String username) {
        return usersRepo.findUsersByUsername(username);
    }
}

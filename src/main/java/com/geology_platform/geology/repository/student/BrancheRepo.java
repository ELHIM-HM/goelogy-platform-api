package com.geology_platform.geology.repository.student;

import com.geology_platform.geology.entity.student.Branche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ELHIM Hamza
 **/

@Repository
public interface BrancheRepo extends JpaRepository<Branche,Long> {
    Optional<Branche> findByName(String branchName);
}

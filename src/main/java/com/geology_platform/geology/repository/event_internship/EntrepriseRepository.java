package com.geology_platform.geology.repository.event_internship;

import com.geology_platform.geology.entity.event_internship.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {

    Optional<Entreprise> findByName(String name);
    boolean existsByName(String name);
}

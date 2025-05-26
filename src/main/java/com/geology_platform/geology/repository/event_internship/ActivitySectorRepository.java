package com.geology_platform.geology.repository.event_internship;

import com.geology_platform.geology.entity.event_internship.ActivitySector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitySectorRepository extends JpaRepository<ActivitySector,Long> {
    boolean existsByLabel(String label);
}

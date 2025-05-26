package com.geology_platform.geology.repository.event_internship;

import com.geology_platform.geology.entity.event_internship.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepository extends JpaRepository<EventCategory,Long> {
    boolean existsByLabel(String label);
}

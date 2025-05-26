package com.geology_platform.geology.repository.event_internship;

import com.geology_platform.geology.entity.event_internship.InternshipCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipCategoryRepository extends JpaRepository<InternshipCategory,Long> {
    InternshipCategory findByLabel(String label);
    boolean existsByLabel(String label);
}

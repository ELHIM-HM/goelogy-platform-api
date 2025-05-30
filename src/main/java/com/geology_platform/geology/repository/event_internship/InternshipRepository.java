package com.geology_platform.geology.repository.event_internship;

import com.geology_platform.geology.entity.event_internship.Internship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InternshipRepository extends JpaRepository<Internship,Long> {

    Page<Internship> findBySectorId(long id, Pageable pageable);
    Page<Internship> findByCategoryId(long id,Pageable pageable);
    Page<Internship> findByCategoryIdAndSectorId(Long categoryId, Long sectorId, Pageable pageable);

}

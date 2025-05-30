package com.geology_platform.geology.repository.event_internship;

import com.geology_platform.geology.entity.event_internship.Event;
import com.geology_platform.geology.entity.event_internship.Internship;
import com.geology_platform.geology.entity.event_internship.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJobRepository extends JpaRepository<Job,Long> {
    Page<Job> findByCategoryId(long id, Pageable pageable);
    Page<Job> findBySectorId(long id,Pageable pageable);
    Page<Job> findByCategoryIdAndSectorId(Long categoryId, Long sectorId, Pageable pageable);



}

package com.geology_platform.geology.repository.event_internship;

import com.geology_platform.geology.entity.event_internship.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    Page<Event> findByCategoryId(long id,Pageable pageable);
    List<Event> findTop3ByOrderByIdDesc();

}

package com.geology_platform.geology.repository.event_internship;

import com.geology_platform.geology.entity.event_internship.Internship;
import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.Thesis;
import com.geology_platform.geology.entity.event_internship.ThesisStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IThesisRepository extends JpaRepository<Thesis,Long> {
    Page<Thesis> findByLevel(Level level, Pageable pageable);
    Page<Thesis> findByStatus(ThesisStatus status, Pageable pageable);
//    Page<Thesis> findBySupervisorId(long id,Pageable pageable);
    Page<Thesis> findByStatusAndLevel(ThesisStatus status, Level level,Pageable pageable);

}

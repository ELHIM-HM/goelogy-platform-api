package com.geology_platform.geology.repository.event_internship;

import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.StudyAnnouncement;
import com.geology_platform.geology.entity.event_internship.Thesis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyAnnouncementRepository extends JpaRepository<StudyAnnouncement,Long> {
    Page<StudyAnnouncement> findByLevel(Level level, Pageable pageable);
}

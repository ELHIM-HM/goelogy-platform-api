package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.StudyAnnouncement;

import java.util.List;

public interface IStudyAnnouncementService {

    List<StudyAnnouncement> getAllAnnouncements(int page, int size);
    List<StudyAnnouncement> getAnnouncementsByLevel(Level level, int page, int size);
    StudyAnnouncement getAnnouncement(long id);
    StudyAnnouncement createAnnouncement(StudyAnnouncement announcement);
    StudyAnnouncement updateAnnouncement(long id,StudyAnnouncement announcement);
    void deleteAnnouncement(long id);

}

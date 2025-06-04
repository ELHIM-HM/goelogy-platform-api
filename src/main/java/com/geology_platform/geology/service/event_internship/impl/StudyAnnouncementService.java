package com.geology_platform.geology.service.event_internship.impl;

import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.StudyAnnouncement;
import com.geology_platform.geology.entity.event_internship.Thesis;
import com.geology_platform.geology.exception.event_internship.StudyAnnouncementException;
import com.geology_platform.geology.repository.event_internship.StudyAnnouncementRepository;
import com.geology_platform.geology.service.event_internship.interfaces.IStudyAnnouncementService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class StudyAnnouncementService implements IStudyAnnouncementService {

    private StudyAnnouncementRepository studyAnnouncementRepo;
    @Override
    public List<StudyAnnouncement> getAllAnnouncements(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudyAnnouncement> announcements = studyAnnouncementRepo.findAll(pageable);
        return announcements.stream().collect(Collectors.toList());
    }

    @Override
    public List<StudyAnnouncement> getAnnouncementsByLevel(Level level, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudyAnnouncement> result ;
        if (level != null) {
            result = studyAnnouncementRepo.findByLevel(level,pageable);
        }else{
            result = studyAnnouncementRepo.findAll(pageable);
        }

        return result.stream().collect(Collectors.toList());
    }

    @Override
    public StudyAnnouncement getAnnouncement(long id) {
        return studyAnnouncementRepo.findById(id)
                .orElseThrow( ()->new StudyAnnouncementException("Announcement couldn't be found"));
    }

    @Override
    public StudyAnnouncement createAnnouncement(StudyAnnouncement announcement) {
        return studyAnnouncementRepo.save(announcement);
    }

    @Override
    public StudyAnnouncement updateAnnouncement(long id, StudyAnnouncement announcement) {
        StudyAnnouncement ann = studyAnnouncementRepo.findById(id)
                .orElseThrow(()->new StudyAnnouncementException("Announcement couldn't be found"));
        ann.setDescription(announcement.getDescription());
        ann.setLevel(announcement.getLevel());
        ann.setTitle(announcement.getTitle());
//        ann.setCreatedAt(announcement.getCreatedAt());
        return ann;
    }

    @Override
    public void deleteAnnouncement(long id) {
        StudyAnnouncement ann = studyAnnouncementRepo.findById(id)
                .orElseThrow(()->new StudyAnnouncementException("Announcement couldn't be found"));
        studyAnnouncementRepo.delete(ann);
    }
}

package com.geology_platform.geology.controller.event_internship;

import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.StudyAnnouncement;
import com.geology_platform.geology.service.event_internship.impl.StudyAnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class StudyAnnouncementController {
    private StudyAnnouncementService announcementService;

    @GetMapping("studyAnnouncement/")
    public List<StudyAnnouncement> getAllAnnouncements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return announcementService.getAllAnnouncements(page, size);
    }
    @GetMapping("studyAnnouncement")
    public List<StudyAnnouncement> getAnnouncementByLevel(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String level
            ){
        return announcementService.getAnnouncementsByLevel(Level.fromString(level),page,size);
    }

    @GetMapping("studyAnnouncement/{id}/")
    public StudyAnnouncement getAnnouncement(@PathVariable long id){
        return announcementService.getAnnouncement(id);
    }

    @PostMapping("studyAnnouncement/")
    @ResponseStatus(HttpStatus.CREATED)
    public StudyAnnouncement createAnnouncement(@RequestBody StudyAnnouncement announcement){
        return announcementService.createAnnouncement(announcement);
    }

    @PutMapping("studyAnnouncement/{id}/")
    public StudyAnnouncement updateAnnouncement(@PathVariable long id,@RequestBody StudyAnnouncement announcement){
        return announcementService.updateAnnouncement(id,announcement);
    }

    @DeleteMapping("studyAnnouncement/{id}/")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable long id){
        announcementService.deleteAnnouncement(id);
        return new ResponseEntity<>("announcement deleted succesfully",HttpStatus.OK);
    }
}

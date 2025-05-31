package com.geology_platform.geology.service.event_internship.impl;

import com.geology_platform.geology.dto.both.EventDTO;
import com.geology_platform.geology.entity.event_internship.Event;
import com.geology_platform.geology.entity.event_internship.EventCategory;
import com.geology_platform.geology.entity.event_internship.Internship;
import com.geology_platform.geology.exception.event_internship.EventCategoryNotFoundException;
import com.geology_platform.geology.exception.event_internship.EventNotFoundException;
import com.geology_platform.geology.repository.event_internship.EventCategoryRepository;
import com.geology_platform.geology.repository.event_internship.EventRepository;
import com.geology_platform.geology.service.event_internship.interfaces.IEventService;
import com.geology_platform.geology.service.fileUpload.FileUploadServiceImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EventServiceImpl implements IEventService {
    private EventRepository eventRepo;
    private EventCategoryRepository categoryRepo;
    private FileUploadServiceImpl fileUploadService;


    @Override
    public List<EventDTO> getAllEvents(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> events = eventRepo.findAll(pageable);
        return events.stream()
                .map((event)->{
                    EventDTO dto = new EventDTO();
                    dto.setId(event.getId());
                    dto.setTitle(event.getTitle());
                    dto.setDescription(event.getDescription());
                    dto.setDate(event.getDate());
                    dto.setLocation(event.getLocation());
                    dto.setSummary(event.getSummary());
                    dto.setCategoryId(event.getCategory().getId());
                    dto.setImg(event.getImg());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getEventsByCategory(long id, int page, int size) {
        EventCategory category =categoryRepo.findById(id)
                .orElseThrow(()-> new EventCategoryNotFoundException("category couldn't be found"));
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> events = eventRepo.findByCategoryId(id,pageable);
        return events.stream()
                .map((event)->{
                    EventDTO dto = new EventDTO();
                    dto.setId(event.getId());
                    dto.setTitle(event.getTitle());
                    dto.setDescription(event.getDescription());
                    dto.setDate(event.getDate());
                    dto.setLocation(event.getLocation());
                    dto.setSummary(event.getSummary());
                    dto.setCategoryId(event.getCategory().getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public EventDTO getEvent(long id) {
        Event event = eventRepo.findById(id)
                .orElseThrow(()->new EventNotFoundException("event couldn't be found"));
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setDate(event.getDate());
        dto.setLocation(event.getLocation());
        dto.setSummary(event.getSummary());
        dto.setCategoryId(event.getCategory().getId());
//        dto.setCategoryLabel(event.getCategory().getLabel());
        return dto;
    }


    public List<EventDTO> getLatest3Events(){
       return eventRepo.findTop3ByOrderByIdDesc()
               .stream().map((event)->{
                   EventDTO dto = new EventDTO();
                   dto.setId(event.getId());
                   dto.setTitle(event.getTitle());
                   dto.setDescription(event.getDescription());
                   dto.setDate(event.getDate());
                   dto.setLocation(event.getLocation());
                   dto.setSummary(event.getSummary());
                   dto.setCategoryId(event.getCategory().getId());
                   return dto;
               }).collect(Collectors.toList());
    }

    @Override
    public EventDTO createEvent(EventDTO dto, MultipartFile img) throws IOException {

        EventCategory category =categoryRepo.findById((long) dto.getCategoryId())
                .orElseThrow(()-> new EventCategoryNotFoundException("category couldn't be found"));

        Event event = new Event();
        event.setImg(fileUploadService.uploadFileToFileSystem(img));
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setDate(dto.getDate());
        event.setLocation(dto.getLocation());
        event.setSummary(dto.getSummary());
        event.setCategory(category);
        eventRepo.save(event);
        dto.setId(event.getId());
        return dto;
    }

    @Override
    public EventDTO updateEvent(long id, EventDTO dto) {
        Event event = eventRepo.findById(id)
                .orElseThrow(()->new EventNotFoundException("event couldn't br found"));

        EventCategory category =categoryRepo.findById((long) dto.getCategoryId())
                .orElseThrow(()-> new EventCategoryNotFoundException("category couldn't be found"));

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setDate(dto.getDate());
        event.setLocation(dto.getLocation());
        event.setSummary(dto.getSummary());
        event.setCategory(category);
        dto.setId(event.getId());
        return dto;
    }

    public void deleteEvent(long id){
        Event event = eventRepo.findById(id)
                .orElseThrow(()->new EventNotFoundException("event couldn't br found"));
        eventRepo.delete(event);
    }


}

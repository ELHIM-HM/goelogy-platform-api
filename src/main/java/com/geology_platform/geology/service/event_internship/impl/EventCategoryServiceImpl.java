package com.geology_platform.geology.service.event_internship.impl;

import com.geology_platform.geology.dto.both.EventCategoryDTO;
import com.geology_platform.geology.entity.event_internship.EventCategory;
import com.geology_platform.geology.entity.event_internship.InternshipCategory;
import com.geology_platform.geology.exception.event_internship.EventCategoryExistsAlready;
import com.geology_platform.geology.exception.event_internship.EventCategoryNotFoundException;
import com.geology_platform.geology.exception.event_internship.InternshipCategoryExistsAlready;
import com.geology_platform.geology.mapper.event_internship.InternshipCategoryMapper;
import com.geology_platform.geology.repository.event_internship.EventCategoryRepository;
import com.geology_platform.geology.service.event_internship.interfaces.IEventCategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EventCategoryServiceImpl implements IEventCategoryService {
    private EventCategoryRepository categoryRepo;

    @Override
    public List<EventCategoryDTO> getAllEvents() {
        return categoryRepo.findAll().stream()
                .map((event)->{
                    EventCategoryDTO dto = new EventCategoryDTO();
                    dto.setLabel(event.getLabel());
                    dto.setId(event.getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public EventCategoryDTO getEventCategory(long id) throws Exception {
        EventCategory category = categoryRepo.findById(id)
                .orElseThrow(()->new EventCategoryNotFoundException("category couldn't be found"));
        EventCategoryDTO dto = new EventCategoryDTO();
        dto.setLabel(category.getLabel());
        dto.setId(category.getId());
        return dto;

    }

    public EventCategoryDTO createEventCategory(EventCategoryDTO dto){
            if(categoryRepo.existsByLabel(dto.getLabel())) throw new EventCategoryExistsAlready("this category exists already");
            System.out.println("=========="+dto.toString());
            EventCategory category = new EventCategory();
            category.setLabel(dto.getLabel());
            categoryRepo.save(category);
            dto.setId(category.getId());
            return dto;
    }

    public EventCategoryDTO updateEventCategory(long id,EventCategoryDTO dto){
        if(categoryRepo.existsByLabel(dto.getLabel())) throw new EventCategoryExistsAlready("this category exists already");

        EventCategory category = categoryRepo.findById(id)
                .orElseThrow(()->new EventCategoryNotFoundException("category couldn't be found"));
        category.setLabel(dto.getLabel());
        dto.setId(category.getId());
        return dto;
    }

    public void deleteEventCategory(long id){
        EventCategory category = categoryRepo.findById(id)
                .orElseThrow(()->new EventCategoryNotFoundException("category couldn't be found"));
        categoryRepo.delete(category);
    }

}

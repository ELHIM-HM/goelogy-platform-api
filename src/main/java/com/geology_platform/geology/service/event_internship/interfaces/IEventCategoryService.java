package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.dto.both.EventCategoryDTO;
import com.geology_platform.geology.dto.both.EventDTO;
import com.geology_platform.geology.entity.event_internship.EventCategory;

import java.util.List;

public interface IEventCategoryService {
    public List<EventCategoryDTO> getAllEvents();
    public EventCategoryDTO getEventCategory(long id) throws Exception;
    public EventCategoryDTO createEventCategory(EventCategoryDTO category);
}

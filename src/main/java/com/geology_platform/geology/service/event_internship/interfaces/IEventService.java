package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.dto.both.EventDTO;
import com.geology_platform.geology.entity.event_internship.Event;

import java.util.List;

public interface IEventService {

    public List<EventDTO> getAllEvents(int page,int size);
    public List<EventDTO>  getEventsByCategory(long id,int page,int size);
    public EventDTO getEvent(long id);
    public EventDTO createEvent(EventDTO dto);
    public EventDTO updateEvent(long id,EventDTO dto);
    public void deleteEvent(long id);
}

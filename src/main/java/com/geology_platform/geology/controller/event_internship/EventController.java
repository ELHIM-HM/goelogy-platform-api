package com.geology_platform.geology.controller.event_internship;

import com.geology_platform.geology.dto.both.EventDTO;
import com.geology_platform.geology.service.event_internship.impl.EventServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class EventController {
    private EventServiceImpl eventService;

    @GetMapping("event/")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDTO> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return eventService.getAllEvents(page,size);
    }

    @GetMapping("event/category/{id}/")
    public List<EventDTO> getEventsByCategory(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return eventService.getEventsByCategory(id,page,size);
    }

    @GetMapping("event/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public EventDTO getEvent(@PathVariable long id) throws Exception {
        return eventService.getEvent(id);
    }

    @GetMapping("event/latest/")
    public List<EventDTO> getLatest3Events(){
        return eventService.getLatest3Events();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("event/")
    public EventDTO createEvent(@RequestPart(name = "event") EventDTO dto
    , @RequestPart(name = "image") MultipartFile img
    ) throws IOException {
        return eventService.createEvent(dto,img);
    }

    @PutMapping("event/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public EventDTO updateEvent(@PathVariable long id, @RequestBody EventDTO dto, @RequestPart(name = "image") MultipartFile img)
    throws IOException
    {
        return eventService.updateEvent(id,dto,img);
    }

    @DeleteMapping("event/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteEvent(@PathVariable long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>("event"+id+"deleted succefully",HttpStatus.OK);
    }

}

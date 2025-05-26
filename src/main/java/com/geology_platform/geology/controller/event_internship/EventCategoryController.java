package com.geology_platform.geology.controller.event_internship;

import com.geology_platform.geology.dto.both.EventCategoryDTO;
import com.geology_platform.geology.entity.event_internship.EventCategory;
import com.geology_platform.geology.service.event_internship.impl.EventCategoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class EventCategoryController {

    EventCategoryServiceImpl categoryService;

    @GetMapping("eventCategory/")
    public List<EventCategoryDTO> getCategories(){
        return categoryService.getAllEvents();
    }

    @GetMapping("eventCategory/{id}/")
    public EventCategoryDTO getCategory(@PathVariable long id) throws Exception {
        return categoryService.getEventCategory(id);
    }

    @PostMapping("eventCategory/")
    @ResponseStatus(HttpStatus.CREATED)
    public EventCategoryDTO createCategory(@RequestBody EventCategoryDTO dto){
        return categoryService.createEventCategory(dto);
    }

    @PutMapping("eventCategory/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public EventCategoryDTO updateCategory(
            @PathVariable long id, @RequestBody EventCategoryDTO dto)
    {
    return categoryService.updateEventCategory(id,dto);
    }

    @DeleteMapping("eventCategory/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCategory(@PathVariable long id){
        categoryService.deleteEventCategory(id);
        return new ResponseEntity<String>("category deleted successfully",HttpStatus.OK);
    }
}

package com.geology_platform.geology.controller.event_internship;


import com.geology_platform.geology.dto.both.InternshipDTO;
import com.geology_platform.geology.service.event_internship.impl.IntershipServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class InternshipController {

    private IntershipServiceImpl internshipService;

    @GetMapping("internship")
    public List<InternshipDTO> getFilteredInternships(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long sectorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return internshipService.getFilteredInternships(categoryId, sectorId, page, size);
    }

    @GetMapping("internship/")
    public List<InternshipDTO> getAllInternships(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return internshipService.getAllInternships(page, size);
    }

    @GetMapping("internship/category/{id}/")
    public List<InternshipDTO> getInternshipsByCategory(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return internshipService.getInternshipsByCategory(id,page,size);
    }

    @GetMapping("internship/sector/{id}/")
    public List<InternshipDTO> getInternshipsBySector(
            @PathVariable long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return internshipService.getInternshipsBySector(id,page,size);
    }

    @GetMapping("internship/{id}/")
    public InternshipDTO getInternshipById(@PathVariable long id) throws Exception {
        return internshipService.getInternshipById(id);
    }
    @PostMapping("internship/")
    @ResponseStatus(HttpStatus.CREATED)
    public InternshipDTO createInternship(@RequestBody InternshipDTO dto){
        return internshipService.createInternship(dto);
    }

    @PutMapping("internship/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public InternshipDTO updateInternship(@PathVariable long id,  @RequestBody InternshipDTO dto) throws Exception {
        return internshipService.updateInternship(id,dto);
    }

    @DeleteMapping("internship/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteInternship(@PathVariable long id) throws Exception {
        internshipService.deleteInternship(id);
        return new ResponseEntity<>("internship deleted",HttpStatus.OK);

    }

}

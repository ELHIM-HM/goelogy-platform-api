package com.geology_platform.geology.controller.event_internship;


import com.geology_platform.geology.dto.both.InternshipDTO;
import com.geology_platform.geology.dto.both.ThesisDTO;
import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.ThesisStatus;
import com.geology_platform.geology.service.event_internship.impl.ThesisServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class ThesisController {
    private ThesisServiceImpl thesisService;

    @GetMapping("thesis")
    public List<ThesisDTO> getFilteredTheses(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        System.out.println("Status reçu : " + status);
        System.out.println("Level reçu : " + level);
        return thesisService.getFilteredTheses(ThesisStatus.fromString(status), Level.fromString(level), page, size);
    }

    @GetMapping("thesis/{thesisId}/")
    public ThesisDTO getThesisById(@PathVariable long thesisId){
        return thesisService.getThesis(thesisId);
    }

    @GetMapping("thesis/")
    public List<ThesisDTO> getAllThesis(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
       return thesisService.getAllThesis(page, size);
    }

    @Operation(description = "retrieves all these with the status provided as a pathvariable\n" +
            "status accepted: 'assigned' , 'available', lowercase or uppercase, doesn't matter ")
    @GetMapping("thesis/status/{status}/")
    public List<ThesisDTO> getThesesByStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
            return thesisService.getThesisByStatus(ThesisStatus.fromString(status),page,size);
    }

    @Operation(description = "retrieves all these with the level provided as a pathvariable\n" +
            "levels accepted: 'licence' , 'master', 'doctorat' lowercase or uppercase, doesn't matter")
    @GetMapping("thesis/level/{level}/")
    public List<ThesisDTO> getThesesByLevel(
            @PathVariable String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        return thesisService.getThesisByLevel(Level.fromString(level),page,size);
    }

    @PostMapping("thesis/")
    @ResponseStatus(HttpStatus.CREATED)
    public ThesisDTO createThesis(
            @RequestBody ThesisDTO dto
            ){
        return thesisService.createThesis(dto);
    }

    @PutMapping("thesis/{thesisId}/")
    public ThesisDTO updateThesis(@PathVariable long thesisId,@RequestBody ThesisDTO dto){
        return thesisService.updateThesis(thesisId,dto);
    }

    @DeleteMapping("thesis/{thesisId}/")
    public ResponseEntity<String> deleteThesis(@PathVariable long thesisId){
        thesisService.deleteThesis(thesisId);
        return new ResponseEntity<>("thesis was deleted succesfully",HttpStatus.OK);
    }

}

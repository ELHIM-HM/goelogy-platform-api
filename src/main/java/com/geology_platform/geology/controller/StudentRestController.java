package com.geology_platform.geology.controller;

import com.geology_platform.geology.dto.student.BrancheDto;
import com.geology_platform.geology.dto.student.FormationDto;
import com.geology_platform.geology.service.student.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author ELHIM Hamza
 **/

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class StudentRestController {

    private StudentServiceImpl studentService;


    @PostMapping("/formations")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFormation(@RequestBody FormationDto formationDto){
        studentService.addFormation(formationDto);
    }

    @PostMapping("/formations/{id}/branches")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBranch(@PathVariable Long id,@RequestBody BrancheDto brancheDto){
        studentService.addBranche(id,brancheDto);
    }

}

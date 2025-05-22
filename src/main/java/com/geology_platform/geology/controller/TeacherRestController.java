package com.geology_platform.geology.controller;

import com.geology_platform.geology.dto.teacher.GradeDto;

import com.geology_platform.geology.entity.teacher.Teacher;
import com.geology_platform.geology.projection.TeacherProjection;
import com.geology_platform.geology.service.teacher.TeacherServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@RestController
@RequestMapping("/api")
@Tag(name = "Teacher Rest Controller")
public class TeacherRestController {

    private TeacherServiceImpl teacherService;

    public TeacherRestController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/grades/{gradeId}/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeacher(@RequestBody Teacher teacher,@PathVariable Long gradeId){
        teacherService.addTeacher(gradeId,teacher);
    }

    @PostMapping("/grades")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGrade(@RequestBody GradeDto grade){
        teacherService.addGrade(grade);
    }

    @GetMapping("/grades")
    @ResponseStatus(HttpStatus.OK)
    public List<GradeDto> loadGrades(){
        return teacherService.loadGrades();
    }


    @GetMapping("/teachers")
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherProjection> loadTeachers(
            @RequestParam(required = false , defaultValue = "0") Integer page,
            @RequestParam(required = false , defaultValue = "10") Integer size,
            @RequestParam(required = false) Long gradeId
    ){
        return teacherService.loadTeachers(page,size,gradeId);
    }




//
    @GetMapping("/teachers/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countTeachers(){
        return teacherService.teachersCount();
    }

    @GetMapping("/teachers/generate-validation-code/{duration}")
    @ResponseStatus(HttpStatus.OK)
    public String generateAccountValidationCode(@PathVariable Long duration){
        return teacherService.generateValidationCode(duration);
    }
//
    @PatchMapping("/teachers/{id}/validation/{isValidated}")
    @ResponseStatus(HttpStatus.OK)
    public void validateTeacher(@PathVariable Long id,@PathVariable boolean isValidated){
         teacherService.validateTeacher(id,isValidated);
    }
//
//    @DeleteMapping("/teachers/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void removeTeacher(@PathVariable Long id){
//        teacherService.removeTeacher(id);
//    }
//
//









}

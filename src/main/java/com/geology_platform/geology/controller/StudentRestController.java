package com.geology_platform.geology.controller;

import com.geology_platform.geology.dto.student.BrancheDto;
import com.geology_platform.geology.dto.student.FormationDto;
import com.geology_platform.geology.entity.student.Student;
import com.geology_platform.geology.projection.StudentProjection;
import com.geology_platform.geology.service.student.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/formations")
    @ResponseStatus(HttpStatus.OK)
    public List<FormationDto> loadFormation(){
        return studentService.loadFormations();
    }

    @GetMapping("/formations/{formationName}/branches")
    @ResponseStatus(HttpStatus.OK)
    public List<BrancheDto> loadBranch(@PathVariable String formationName){
        return studentService.loadBranches(formationName);
    }


    @PostMapping("/branches/{branchName}/students/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBranch(@PathVariable String branchName,@RequestBody Student student,@PathVariable String code){
        studentService.addStudent(branchName,student,code);
    }

    @GetMapping("/students/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countStudent(){
        return studentService.countStudent();
    }


    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeStudent(@PathVariable Long id){
         studentService.removeStudent(id);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentProjection> loadAllStudents(@RequestParam(required = false,defaultValue = "0") Integer page,
                                                   @RequestParam(required = false,defaultValue = "10") Integer size,
                                                   @RequestParam(required = false) Long brancheId){
        return studentService.loadAllStudents(page, size, brancheId);
    }

//    @DeleteMapping("/formations")
//    @ResponseStatus(HttpStatus.OK)
//    public void removeFormations(){
//        studentService.deleteAllFormation();
//    }




}

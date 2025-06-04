package com.geology_platform.geology.controller;

import com.geology_platform.geology.dto.classroom.*;
import com.geology_platform.geology.service.classroom.ClassroomServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/

@RestController
@RequestMapping("/api/classrooms")
@AllArgsConstructor
public class ClassroomRestController {

    private ClassroomServiceImpl classroomService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addClassroom(@RequestPart(required = true,name = "class") ClassroomDto classroomDto, @RequestPart(required = false,name = "image") MultipartFile coverImage) throws IOException {
        classroomService.addClassroom(classroomDto,coverImage);
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudentToClassroom(@RequestBody JoinClassroomDto joinClassroomDto){
        classroomService.joinClassroom(joinClassroomDto);
    }


    @GetMapping("/{studentEmail}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassroomInfoDto> loadStudentClassrooms(@PathVariable String studentEmail){
        return classroomService.laodStudentClassrooms(studentEmail);
    }


    @PostMapping("/{id}/subjects")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSubject(@RequestBody AddSubjectDto addSubjectDto,@PathVariable Long id){
        classroomService.addSubject(id,addSubjectDto);
    }

    @GetMapping("/{id}/subjects")
    @ResponseStatus(HttpStatus.OK)
    public Set<ReadSubjectDto> loadStudentClassrooms(@PathVariable Long id){
        return classroomService.loadAllSubjects(id);
    }


    @PostMapping("/subjects/{id}/materials")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMaterial(@PathVariable Long id ,@RequestPart(name = "material") AddMaterialDto materialDto,@RequestPart(name = "file") MultipartFile file ) throws IOException {
        classroomService.addMaterial(id,materialDto,file);
    }


    @GetMapping("/subjects/{id}/materials")
    @ResponseStatus(HttpStatus.OK)
    public List<ReadMaterialDto> loadSubjectMatials(@PathVariable Long id){
        return classroomService.loadSubjectMaterials(id);
    }

    @GetMapping("/{id}/materials")
    @ResponseStatus(HttpStatus.OK)
    public List<ReadTeacherMaterialDto> loadClassMaterials(@PathVariable Long id){
        return classroomService.loadClassroomMaterials(id);
    }

    @DeleteMapping("/materials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeMaterial(@PathVariable Long id){
         classroomService.removeMaterial(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeClass(@PathVariable Long id){
        classroomService.removeClass(id);
    }

    @DeleteMapping("/subjects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeSubject(@PathVariable Long id){
        classroomService.removeSubject(id);
    }

    @PatchMapping("/subjects")
    @ResponseStatus(HttpStatus.OK)
    public void updateSubject(@RequestBody ReadSubjectDto subjectDto){
        classroomService.updateSubject(subjectDto);
    }




    @GetMapping("/teacher/{email}/summary")
    @ResponseStatus(HttpStatus.OK)
    public TeacherHerderInfoDto loadTeacherClassroomsSummary(@PathVariable String email){
        return classroomService.loadTeacherClassroomsSummary(email);
    }

    @GetMapping("/teacher/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherClassroomResponseDto> loadTeacherClassrooms(@PathVariable String email){
        return classroomService.loadTeacherClassrooms(email);
    }




}

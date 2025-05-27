package com.geology_platform.geology.controller.event_internship;

import com.geology_platform.geology.dto.both.JobDTO;
import com.geology_platform.geology.service.event_internship.impl.JobServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class JobController {
    private JobServiceImpl jobService;


    @GetMapping("job/{id}/")
    public JobDTO getJob(@PathVariable int id){
        return jobService.getJob(id);
    }

    @GetMapping("job/")
    public List<JobDTO> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        System.out.println("getAllJobs trigerred");
        return jobService.getAllJobs(page,size);
    }

    @GetMapping("job/category/{categoryId}/")
    public List<JobDTO> getJobsByCategory(
            @PathVariable int categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ){
        return jobService.getJobsByCategory(categoryId,page,size);
    }

    @GetMapping("job/sector/{sectorId}/")
    public List<JobDTO> getJobsSector(
            @PathVariable int sectorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ){
        return jobService.getJobsBySector(sectorId,page,size);
    }

    @PostMapping("job/")
    @ResponseStatus(HttpStatus.CREATED)
    public JobDTO createJob(@RequestBody JobDTO dto){
        System.out.println(dto.getCategoryId());
        System.out.println(dto);
        return jobService.createJob(dto);
    }

    @PutMapping("job/{id}/")
    public JobDTO updateJob(@PathVariable int id,@RequestBody JobDTO dto){
        return jobService.updateJob(id,dto);
    }

    @DeleteMapping("job/{id}/")
    public ResponseEntity<String> deleteJob(@PathVariable int id){
        jobService.deleteJob(id);
        return new ResponseEntity<>("job deleted succesfully", HttpStatus.OK);
    }




}

package com.geology_platform.geology.controller.event_internship;

import com.geology_platform.geology.dto.both.ActivitySectorDTO;
import com.geology_platform.geology.repository.event_internship.ActivitySectorRepository;
import com.geology_platform.geology.service.event_internship.impl.ActivitySectorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class ActivitySectorController {

    private ActivitySectorServiceImpl sectorService;

    @GetMapping("sector/{id}/")
    public ActivitySectorDTO getSector(@PathVariable long id){
        return sectorService.getActivitySector(id);
    }
    @GetMapping("sector/")
    public List<ActivitySectorDTO> getSectors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return sectorService.getSectors(page,size);
    }


    @PostMapping("sector/")
    @ResponseStatus(HttpStatus.CREATED)
    public ActivitySectorDTO createSector(@RequestBody ActivitySectorDTO dto){
        return sectorService.createActivitySector(dto);
    }

    @PutMapping("sector/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public ActivitySectorDTO updateSector(@PathVariable long id,@RequestBody ActivitySectorDTO dto){
        return sectorService.updateSector(id,dto);
    }

    @DeleteMapping("sector/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteSector(@PathVariable long id){
        sectorService.deleteSector(id);
        return new ResponseEntity<>("Sector delete succesfully",HttpStatus.OK);
    }

}

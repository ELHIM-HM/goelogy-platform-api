package com.geology_platform.geology.controller.event_internship;

import com.geology_platform.geology.entity.event_internship.Entreprise;
import com.geology_platform.geology.service.event_internship.impl.EntrepriseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class EntrepriseController {
    private EntrepriseService entrepriseService;

    @GetMapping("entreprise/")
    public List<Entreprise> getAllEntreprises(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return entrepriseService.getAllEntreprises(page, size);
    }

    @GetMapping("entreprise/{id}/")
    public Entreprise getEntreprise(@PathVariable long id){
        return entrepriseService.getEntreprise(id);
    }

    @PostMapping("entreprise/")
    @ResponseStatus(HttpStatus.CREATED)
    public Entreprise createEntreprise(@RequestBody Entreprise ent){
        return entrepriseService.createEntreprise(ent);
    }

    @PutMapping("entreprise/{id}/")
    public Entreprise updateEntreprise(@PathVariable long id, @RequestBody Entreprise entreprise){
        return entrepriseService.updateEntreprise(id,entreprise);
    }

    @DeleteMapping("entreprise/{id}/")
    public ResponseEntity<String> deleteEntreprise(@PathVariable long id){
         entrepriseService.deleteEntreprise(id);
         return new ResponseEntity<>("entreprise deleted succesfully",HttpStatus.OK);
    }


}

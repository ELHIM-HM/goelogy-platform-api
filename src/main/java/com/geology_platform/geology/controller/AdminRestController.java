package com.geology_platform.geology.controller;

import com.geology_platform.geology.entity.user.Authority;
import com.geology_platform.geology.service.admine.AdmineServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Tag(name = "Admin controller")
public class AdminRestController {

    private AdmineServiceImpl admineService;


    @PostMapping("/authorities")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAuthority(@RequestBody Authority authority){
        admineService.addAuthority(authority);
    }

    @GetMapping("/authorities")
    @ResponseStatus(HttpStatus.OK)
    public List<Authority> loadAuthorities(){
       return admineService.loadAllAuthorities();
    }

    @DeleteMapping("/authorities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void loadAuthorities(@PathVariable Long id){
         admineService.removeAuthority(id);
    }

}

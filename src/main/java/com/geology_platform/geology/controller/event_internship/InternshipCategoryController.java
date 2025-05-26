package com.geology_platform.geology.controller.event_internship;

import com.geology_platform.geology.dto.both.InternshipCategoryDTO;
import com.geology_platform.geology.dto.both.InternshipDTO;
import com.geology_platform.geology.service.event_internship.impl.InternshipCategoryServiceImpl;
import com.geology_platform.geology.service.event_internship.impl.IntershipServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class InternshipCategoryController {
    private InternshipCategoryServiceImpl categoryService;

    @GetMapping("internshipCategory/")
    public List<InternshipCategoryDTO> getAllInternshipCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("internshipCategory/{id}/")
    public InternshipCategoryDTO getCategoryById(@PathVariable long id) throws Exception {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("internshipCategory/create/")
    @ResponseStatus(HttpStatus.CREATED)
    public InternshipCategoryDTO createCategory(@RequestBody InternshipCategoryDTO dto){
        return categoryService.createCategory(dto);
    }

    @PutMapping("internshipCategory/update/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public  InternshipCategoryDTO updateCategory( @PathVariable long id, @RequestBody InternshipCategoryDTO dto) throws Exception {
        System.out.println("====updating category.....");
        return categoryService.updateCategory(id,dto);
    }

    @DeleteMapping("internshipCategory/delete/{id}/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCategory( @PathVariable int id) throws Exception {
        System.out.println("====deleting category.....");
        categoryService.deleteCategory(id);
        return new ResponseEntity<String>("category deleted succesfully",HttpStatus.OK);
    }
}

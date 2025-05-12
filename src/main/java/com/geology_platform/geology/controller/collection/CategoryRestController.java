package com.geology_platform.geology.controller.collection;

import com.geology_platform.geology.dto.request.collection.RequestCategory;
import com.geology_platform.geology.dto.request.collection.RequestSubCategory;
import com.geology_platform.geology.dto.response.collection.ResponseCategory;
import com.geology_platform.geology.dto.response.collection.ResponseSubCategory;
import com.geology_platform.geology.service.collection.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/


@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryRestController {

    private CategoryService categoryService;

    //    ============== crud operation for for  categories and sub categories all tested =========


    @Operation(summary = "Create a new category" )
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@Valid @RequestBody RequestCategory requestCategory){
        categoryService.createCategory(requestCategory);
    }


    @Operation(summary = "Get all categories")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseCategory> getAllCategories(){
        return  categoryService.getAllCategories();
    }

    @Operation(summary = "Delete all categories")
    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllCategories(){
        categoryService.getAllCategories();
    }


//    @PutMapping("/{categoryId}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateCategory(@PathVariable Long categoryId,
//                               @Valid @RequestBody RequestCategory requestCategory){
//        categoryService.updateCategory(categoryId,requestCategory);
//    }


    @Operation(summary = "Create a new subcategory")
    @PostMapping("/{categoryId}/subCategories")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSubCategory(@PathVariable Long categoryId,@RequestBody RequestSubCategory subCategory){
        categoryService.createSubCategory(categoryId,subCategory);
    }


    @Operation(summary = "Get all subcategories for a category")
    @GetMapping("/{categoryId}/subcategories")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseSubCategory> getAllSubCategories(@PathVariable Long categoryId){
        return  categoryService.getAllSubCategories(categoryId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a subcategory by id")
    @DeleteMapping("/subcategories/{subCategoryId}")
    public void deleteSubCategory(@PathVariable Long subCategoryId){
        categoryService.deleteSubCategory(subCategoryId);
    }


}

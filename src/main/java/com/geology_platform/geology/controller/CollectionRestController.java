package com.geology_platform.geology.controller;


import com.geology_platform.geology.dto.request.collection.RequestCategory;
import com.geology_platform.geology.dto.request.collection.RequestRockModel;
import com.geology_platform.geology.dto.request.collection.RequestSubCategory;
import com.geology_platform.geology.dto.response.collection.ResponseCategory;
import com.geology_platform.geology.dto.response.collection.ResponseSubCategory;
import com.geology_platform.geology.service.collection.CollectionServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author ELHIM Hamza
 **/

@RestController
@RequestMapping(name = "collections")
@AllArgsConstructor
public class CollectionRestController {

    private CollectionServiceImpl collectionService;

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@Valid @RequestBody RequestCategory requestCategory){
        collectionService.createCategory(requestCategory);
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseCategory> getAllCategories(){
        return  collectionService.getAllCategories();
    }

    @DeleteMapping("/categories")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllCategories(){
        collectionService.getAllCategories();
    }



    @PostMapping("/{categoryId}/subCategories")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSubCategory(@PathVariable Long categoryId,@RequestBody RequestSubCategory subCategory){
        collectionService.createSubCategory(categoryId,subCategory);
    }

    @GetMapping("/{categoryId}/subCateries")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseSubCategory> getAllSubCategories(@PathVariable Long categoryId){
        return  collectionService.getAllSubCategories(categoryId);
    }



    @PostMapping("/rocks")
    @ResponseStatus(HttpStatus.CREATED)
    public void createModelItem(@RequestBody RequestRockModel requestRockModel){
        collectionService.createRockModel(requestRockModel);
    }

    @GetMapping("/rocks")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestRockModel> getAllRockModels(){
        return  collectionService.getAllRockModels();
    }















}

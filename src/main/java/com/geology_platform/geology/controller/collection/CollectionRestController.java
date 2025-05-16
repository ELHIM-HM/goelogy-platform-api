package com.geology_platform.geology.controller.collection;


import com.geology_platform.geology.dto.request.collection.RequestFossilModel;
import com.geology_platform.geology.dto.request.collection.RequestMineraisModel;
import com.geology_platform.geology.dto.request.collection.RequestMineralModel;
import com.geology_platform.geology.dto.request.collection.RequestRockModel;
import com.geology_platform.geology.dto.response.collection.ResponseModelItem;
import com.geology_platform.geology.mapper.collection.FossilModelMapper;
import com.geology_platform.geology.service.collection.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author ELHIM Hamza
 **/

@RestController
@RequestMapping("/models")
@AllArgsConstructor
@Tag(name = "3d models api")
public class CollectionRestController {

    private CollectionService collectionService;








    @Operation(summary = "Create a new rock model")
    @PostMapping("/rocks")
    @ResponseStatus(HttpStatus.CREATED)
    public void createModelItem(@RequestBody RequestRockModel requestRockModel){
        collectionService.createRockModel(requestRockModel);
    }

    @Operation(summary = "Create a new mineral model")
    @PostMapping("/minerals")
    @ResponseStatus(HttpStatus.CREATED)
    public void createModelItem(@RequestBody RequestMineralModel requestMineralModel){
        collectionService.createMineralModel(requestMineralModel);
    }

    @Operation(summary = "Create a new minerais model")
    @PostMapping("/minerais")
    @ResponseStatus(HttpStatus.CREATED)
    public void createModelItem(@RequestBody RequestMineraisModel requestMineraisModel){
        collectionService.createMineraisModel(requestMineraisModel);
    }

    @Operation(summary = "Create a new fossil model")
    @PostMapping("/fossils")
    @ResponseStatus(HttpStatus.CREATED)
    public void createModelItem(@RequestBody RequestFossilModel requestFossilModel){
        collectionService.createFossilModel(requestFossilModel);
    }



    @Operation(summary = "Get a specific  model by ID")
    @GetMapping("/{modelId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModelItem getModelItem(@PathVariable Long modelId){
        return collectionService.getModelById(modelId);
    }

    @Operation(summary = "Delete a specific  model by ID")
    @DeleteMapping("/{modelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModelItem(@PathVariable Long modelId){
        collectionService.deleteModelById(modelId);
    }







    @Operation(summary = "update rock model by model ID")
    @PutMapping("/rocks/{modelId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRockModel(@PathVariable Long modelId,@RequestBody RequestRockModel requestRockModel){
        collectionService.updateRockModel(modelId,requestRockModel);
    }

    @Operation(summary = "update fossil model by model ID")
    @PutMapping("/fossils/{modelId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateFossilModel(@PathVariable Long modelId,@RequestBody RequestFossilModel requestFossilModel){
        collectionService.updateFossilModel(modelId,requestFossilModel);
    }

    @Operation(summary = "update mineral model by model ID")
    @PutMapping("/minerals/{modelId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMineralModel(@PathVariable Long modelId,@RequestBody RequestMineralModel requestMineralModel){
        collectionService.updateMineralModel(modelId,requestMineralModel);
    }

    @Operation(summary = "update minerais model by model ID")
    @PutMapping("/minerais/{modelId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMineraisModel(@PathVariable Long modelId,@RequestBody RequestMineraisModel requestMineraisModel){
        collectionService.updateMineraisModel(modelId,requestMineraisModel);
    }






    @Operation(summary = "Get all Models felted  ( the first 10 )  in a random way ")
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseModelItem> getShuffledModels(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer size,
                                                     @Parameter(description = "pass the category key") @RequestParam(required = false) String category ,
                                                    @Parameter(description = "pass the subcategory name") @RequestParam(required = false) String subcategory){
        return collectionService.getShuffledModels(page, size,category,subcategory);
    }





}

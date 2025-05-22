package com.geology_platform.geology.service.collection;

import com.geology_platform.geology.dto.request.collection.RequestFossilModel;
import com.geology_platform.geology.dto.request.collection.RequestMineraisModel;
import com.geology_platform.geology.dto.request.collection.RequestMineralModel;
import com.geology_platform.geology.dto.request.collection.RequestRockModel;
import com.geology_platform.geology.dto.response.collection.ResponseModelItem;
import com.geology_platform.geology.entity.collection.ModelItem;
import com.geology_platform.geology.entity.collection.SubCategory;
import com.geology_platform.geology.exception.collection.ModelNotFound;
import com.geology_platform.geology.exception.collection.SubCategoryNotFound;
import com.geology_platform.geology.mapper.collection.*;
import com.geology_platform.geology.repository.collection.*;
import com.geology_platform.geology.service.fileUpload.FileUploadServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ELHIM Hamza
 **/


@AllArgsConstructor
@Service
public class CollectionService {

    private final ModelItemMapper modelItemMapper;

    private SubCateogryRepo subCateogryRepo;


    private FossilModelMapper fossilModelMapper;
    private MineralModelMapper mineralModelMapper;


    private ModelItemRepo modelItemRepo;
    private OtherInfosRepo otherInfosRepo;
    private RockPropRepo rockPropRepo;
    private MineralPropRepo mineralPropRepo;
    private FossilPropRepo fossilPropRepo;

    private RockModelMapper rockModelMapper;

    private MineraisMapper mineraisMapper;
    private MineraisRepo mineraisRepo;

    private FileUploadServiceImpl fileUploadService;


    @Transactional
    public void createRockModel(RequestRockModel requestRockModel, MultipartFile model3d,MultipartFile video) throws IOException {

        SubCategory subCategory = subCateogryRepo.findById(requestRockModel.getSubcategoryId()).orElseThrow(()->new SubCategoryNotFound(requestRockModel.getSubcategoryId()));

        System.out.println("sub category found"+subCategory);

        requestRockModel.setCategoryName("rock");

        requestRockModel.setModel3d(fileUploadService.uploadFileToFileSystem(model3d));
        requestRockModel.setVideo(fileUploadService.uploadFileToFileSystem(video));


//        ModelItem modelItem = modelItemRepo.save(rockModelMapper.toEntity(requestRockModel));

        subCategory.addModelItem(rockModelMapper.toEntity(requestRockModel));

        System.out.println("saving sub category");

        subCateogryRepo.save(subCategory);


    }


    @Transactional
    public void createFossilModel(RequestFossilModel requestFossilModel,MultipartFile model3d,MultipartFile video) throws IOException {

        SubCategory subCategory = subCateogryRepo.findById(requestFossilModel.getSubcategoryId()).orElseThrow(()->new SubCategoryNotFound(requestFossilModel.getSubcategoryId()));

        requestFossilModel.setCategoryName("fossil");

        requestFossilModel.setModel3d(fileUploadService.uploadFileToFileSystem(model3d));
        requestFossilModel.setVideo(fileUploadService.uploadFileToFileSystem(video));


        subCategory.addModelItem(fossilModelMapper.toEntity(requestFossilModel));

        subCateogryRepo.save(subCategory);


    }

    @Transactional
    public void createMineralModel(RequestMineralModel requestMineralModel,MultipartFile model3d,MultipartFile video) throws IOException {

        SubCategory subCategory = subCateogryRepo.findById(requestMineralModel.getSubcategoryId()).orElseThrow(()->new SubCategoryNotFound(requestMineralModel.getSubcategoryId()));

        requestMineralModel.setCategoryName("mineral");

        requestMineralModel.setModel3d(fileUploadService.uploadFileToFileSystem(model3d));
        requestMineralModel.setVideo(fileUploadService.uploadFileToFileSystem(video));


        subCategory.addModelItem(mineralModelMapper.toEntity(requestMineralModel));

        subCateogryRepo.save(subCategory);


    }

    @Transactional
    public void createMineraisModel(RequestMineraisModel requestMineraisModel,MultipartFile model3d,MultipartFile video) throws IOException {

        SubCategory subCategory = subCateogryRepo.findById(requestMineraisModel.getSubcategoryId()).orElseThrow(()->new SubCategoryNotFound(requestMineraisModel.getSubcategoryId()));

        requestMineraisModel.setCategoryName("minerais");

        requestMineraisModel.setModel3d(fileUploadService.uploadFileToFileSystem(model3d));
        requestMineraisModel.setVideo(fileUploadService.uploadFileToFileSystem(video));


        subCategory.addModelItem(mineraisMapper.toEntity(requestMineraisModel));

        subCateogryRepo.save(subCategory);


    }


    public ResponseModelItem getModelById(Long modelId){
        ModelItem modelItem = modelItemRepo.findById(modelId).orElseThrow(()->new ModelNotFound(modelId));
        return modelItemMapper.toResponseModelItem(modelItem,modelItem.getCategoryName());
    }


    @Transactional
    public void deleteModelById(Long modelId){
        ModelItem modelItem = modelItemRepo.findById(modelId).orElseThrow(()->new ModelNotFound(modelId));
        modelItemRepo.delete(modelItem);
    }



    @Transactional
    public void updateRockModel(Long modelId,RequestRockModel requestRockModel){
        ModelItem modelItem = modelItemRepo.findById(modelId).orElseThrow(()->new ModelNotFound(modelId));


        modelItem.setInventoryNumber(requestRockModel.getInventoryNumber());



        if(requestRockModel.getOtherInfos() != null){
            otherInfosRepo.deleteAll(modelItem.getOtherInfos());
            modelItem.setOtherInfos(requestRockModel.getOtherInfos());
        }


        rockPropRepo.delete(modelItem.getRockProperties());
        modelItem.setRockProperties(requestRockModel.getRockProperties());


        modelItemRepo.save(modelItem);


    }

    @Transactional
    public void updateMineralModel(Long modelId,RequestMineralModel requestMineralModel){
        ModelItem modelItem = modelItemRepo.findById(modelId).orElseThrow(()->new ModelNotFound(modelId));

        modelItem.setInventoryNumber(requestMineralModel.getInventoryNumber());



        if(requestMineralModel.getOtherInfos() != null){
            otherInfosRepo.deleteAll(modelItem.getOtherInfos());
            modelItem.setOtherInfos(requestMineralModel.getOtherInfos());
        }


        mineralPropRepo.delete(modelItem.getMineralProperties());
        modelItem.setMineralProperties(requestMineralModel.getMineralProperties());


        modelItemRepo.save(modelItem);

    }


    @Transactional
    public void updateMineraisModel(Long modelId,RequestMineraisModel requestMineraisModel){
        ModelItem modelItem = modelItemRepo.findById(modelId).orElseThrow(()->new ModelNotFound(modelId));

        modelItem.setInventoryNumber(requestMineraisModel.getInventoryNumber());



        if(requestMineraisModel.getOtherInfos() != null){
            otherInfosRepo.deleteAll(modelItem.getOtherInfos());
            modelItem.setOtherInfos(requestMineraisModel.getOtherInfos());
        }


        mineraisRepo.delete(modelItem.getMineraisProperties());
        modelItem.setMineraisProperties(requestMineraisModel.getMineraisProperties());


        modelItemRepo.save(modelItem);

    }

    @Transactional
    public void updateFossilModel(Long modelId,RequestFossilModel requestFossilModel){
        ModelItem modelItem = modelItemRepo.findById(modelId).orElseThrow(()->new ModelNotFound(modelId));

        modelItem.setInventoryNumber(requestFossilModel.getInventoryNumber());



        if(requestFossilModel.getOtherInfos() != null){
            otherInfosRepo.deleteAll(modelItem.getOtherInfos());
            modelItem.setOtherInfos(requestFossilModel.getOtherInfos());
        }


        fossilPropRepo.delete(modelItem.getFossilProperties());
        modelItem.setFossilProperties(requestFossilModel.getFossilProperties());


        modelItemRepo.save(modelItem);

    }



    public List<ResponseModelItem> getShuffledModels(Integer page, Integer size,String category,String subcategory) {
        if (size == null || size <= 0) size = 10;
        if (page == null || page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size);


        Page<ModelItem> shuffledPage ;

        if(subcategory != null){
            Long subCategoryId = subCateogryRepo.findByNameIgnoreCase(subcategory).getSubCategoryId();
            shuffledPage =  modelItemRepo.findModelsBySubcategoryId(subCategoryId,pageable);
        }else if(category != null){
            shuffledPage = modelItemRepo.findModelItemByCategoryNameOrderByModelIdDesc(pageable,category);
        }else {
            shuffledPage = modelItemRepo.findAllByOrderByModelIdDesc(pageable);
        }


        return shuffledPage.getContent().stream()
                .map(model -> modelItemMapper.toResponseModelItem(model,model.getCategoryName())).collect(Collectors.toList());

    }
}

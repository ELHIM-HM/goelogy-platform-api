package com.geology_platform.geology.service.collection;

import com.geology_platform.geology.dto.request.collection.RequestCategory;
import com.geology_platform.geology.dto.request.collection.RequestRockModel;
import com.geology_platform.geology.dto.request.collection.RequestSubCategory;
import com.geology_platform.geology.dto.response.collection.ResponseCategory;
import com.geology_platform.geology.dto.response.collection.ResponseSubCategory;
import com.geology_platform.geology.entity.collection.Category;
import com.geology_platform.geology.entity.collection.ModelItem;
import com.geology_platform.geology.entity.collection.SubCategory;
import com.geology_platform.geology.exception.collection.CategoryAlreadyExist;
import com.geology_platform.geology.exception.collection.CategoryNotFound;
import com.geology_platform.geology.exception.collection.SubCategoryAlreadyExist;
import com.geology_platform.geology.exception.collection.SubCategoryNotFound;
import com.geology_platform.geology.mapper.collection.CategoryMapper;
import com.geology_platform.geology.mapper.collection.RockModelMapper;
import com.geology_platform.geology.mapper.collection.SubCategoryMapper;
import com.geology_platform.geology.repository.collection.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/


@AllArgsConstructor
@Service
public class CollectionServiceImpl implements CollectionService{

    private CategoryRepo categoryRepo;
    private SubCateogryRepo subCateogryRepo;
    private ModelItemRepo modelItemRepo;
    private OtherInfosRepo otherInfosRepo;
    private RockPropRepo rockPropRepo;
    private MineralPropRepo mineralPropRepo;
    private FossilPropRepo fossilPropRepo;
    private CategoryMapper categoryMapper;
    private SubCategoryMapper subCategoryMapper;
    private RockModelMapper rockModelMapper;

    @Transactional
    @Override
    public void createCategory(RequestCategory requestCategory) {

        if(categoryRepo.existsByName(requestCategory.getName()))
            throw new CategoryAlreadyExist(requestCategory.getName());

        categoryRepo.save(categoryMapper.toEntity(requestCategory));
    }

    @Transactional
    @Override
    public void createSubCategory(Long categoryID, RequestSubCategory requestSubCategory) {
        if(subCateogryRepo.existsByName(requestSubCategory.getName()))
            throw new SubCategoryAlreadyExist(requestSubCategory.getName());

        Category category = categoryRepo.findById(categoryID).orElseThrow(()->new CategoryNotFound(categoryID));
        SubCategory subCategory = subCategoryMapper.toEntity(requestSubCategory);


        category.addSubCategory(subCategory);

        subCateogryRepo.save(subCategory);

    }

    @Override
    public List<ResponseCategory> getAllCategories() {
        return categoryMapper.toResponseList(categoryRepo.findAll());
    }

    @Override
    public List<ResponseSubCategory> getAllSubCategories(Long categoryID) {

        Category category = categoryRepo.findWithSubCategoriesByCategoryId(categoryID).orElseThrow(()->new CategoryNotFound(categoryID));



        return subCategoryMapper.toResponseList(category.getSubCategories());
    }

    @Transactional
    @Override
    public void deleteAllCategories() {
        categoryRepo.deleteAll();
    }


    @Transactional
    @Override
    public void createRockModel(RequestRockModel requestRockModel) {

        SubCategory subCategory = subCateogryRepo.findById(requestRockModel.getSubcategoryId()).orElseThrow(()->new SubCategoryNotFound(requestRockModel.getSubcategoryId()));

        subCategory.addModelItem(rockModelMapper.toEntity(requestRockModel));

        subCateogryRepo.save(subCategory);
    }

    @Override
    public List<RequestRockModel> getAllRockModels() {
        return rockModelMapper.toResponseList(modelItemRepo.findAll());
    }


}

package com.geology_platform.geology.service.collection;

import com.geology_platform.geology.dto.request.collection.RequestCategory;
import com.geology_platform.geology.dto.request.collection.RequestSubCategory;
import com.geology_platform.geology.dto.response.collection.ResponseCategory;
import com.geology_platform.geology.dto.response.collection.ResponseSubCategory;
import com.geology_platform.geology.entity.collection.Category;
import com.geology_platform.geology.entity.collection.SubCategory;
import com.geology_platform.geology.exception.collection.CategoryAlreadyExist;
import com.geology_platform.geology.exception.collection.CategoryNotFound;
import com.geology_platform.geology.exception.collection.SubCategoryAlreadyExist;
import com.geology_platform.geology.exception.collection.SubCategoryNotFound;
import com.geology_platform.geology.mapper.collection.CategoryMapper;
import com.geology_platform.geology.mapper.collection.SubCategoryMapper;
import com.geology_platform.geology.repository.collection.CategoryRepo;
import com.geology_platform.geology.repository.collection.SubCateogryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/


@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepo categoryRepo;
    private SubCateogryRepo subCateogryRepo;
    private CategoryMapper categoryMapper;
    private SubCategoryMapper subCategoryMapper;


    @Transactional
    public void createCategory(RequestCategory requestCategory) {

        if(categoryRepo.existsByName(requestCategory.getName()))
            throw new CategoryAlreadyExist(requestCategory.getName());

        categoryRepo.save(categoryMapper.toEntity(requestCategory));
    }

    @Transactional
    public void createSubCategory(Long categoryID, RequestSubCategory requestSubCategory) {
        if(subCateogryRepo.existsByName(requestSubCategory.getName()))
            throw new SubCategoryAlreadyExist(requestSubCategory.getName());

        Category category = categoryRepo.findById(categoryID).orElseThrow(()->new CategoryNotFound(categoryID));
        SubCategory subCategory = subCategoryMapper.toEntity(requestSubCategory);


        category.addSubCategory(subCategory);

        subCateogryRepo.save(subCategory);

    }

    public List<ResponseCategory> getAllCategories() {
        return categoryMapper.toResponseList(categoryRepo.findAll());
    }

    public List<ResponseSubCategory> getAllSubCategories(Long categoryID) {

        Category category = categoryRepo.findWithSubCategoriesByCategoryId(categoryID).orElseThrow(()->new CategoryNotFound(categoryID));



        return subCategoryMapper.toResponseList(category.getSubCategories());
    }

    @Transactional
    public void deleteSubCategory(Long subCategoryId) {

        SubCategory subCategory = subCateogryRepo.findById(subCategoryId).orElseThrow(()->new SubCategoryNotFound(subCategoryId));

        subCateogryRepo.delete(subCategory);
    }

    @Transactional
    public void deleteAllCategories() {
        categoryRepo.deleteAll();
    }

}

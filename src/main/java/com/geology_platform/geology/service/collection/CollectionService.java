package com.geology_platform.geology.service.collection;

import com.geology_platform.geology.dto.request.collection.RequestCategory;
import com.geology_platform.geology.dto.request.collection.RequestRockModel;
import com.geology_platform.geology.dto.request.collection.RequestSubCategory;
import com.geology_platform.geology.dto.response.collection.ResponseCategory;
import com.geology_platform.geology.dto.response.collection.ResponseSubCategory;
import com.geology_platform.geology.entity.collection.Category;
import com.geology_platform.geology.entity.collection.ModelItem;
import com.geology_platform.geology.entity.collection.SubCategory;

import java.util.List;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/
public interface CollectionService {


    public void createCategory(RequestCategory requestCategory);
    public void createSubCategory(Long categoryID, RequestSubCategory requestSubCategory);

    public List<ResponseCategory> getAllCategories();

    public List<ResponseSubCategory> getAllSubCategories(Long categoryID);

    public void deleteAllCategories();

    public void createRockModel(RequestRockModel requestRockModel);


    public List<RequestRockModel> getAllRockModels();

}

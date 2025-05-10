package com.geology_platform.geology.mapper.collection;

import com.geology_platform.geology.dto.request.collection.RequestCategory;
import com.geology_platform.geology.dto.request.collection.RequestSubCategory;
import com.geology_platform.geology.dto.response.collection.ResponseCategory;
import com.geology_platform.geology.dto.response.collection.ResponseSubCategory;
import com.geology_platform.geology.entity.collection.Category;
import com.geology_platform.geology.entity.collection.SubCategory;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {


        SubCategory toEntity(RequestSubCategory requestSubCategory);

        ResponseCategory toResponse(SubCategory subCategory);

        List<ResponseSubCategory> toResponseList(Set<SubCategory> subCategories);



}

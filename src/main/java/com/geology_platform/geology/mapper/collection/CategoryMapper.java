package com.geology_platform.geology.mapper.collection;

import com.geology_platform.geology.dto.request.collection.RequestCategory;
import com.geology_platform.geology.dto.response.collection.ResponseCategory;
import com.geology_platform.geology.entity.collection.Category;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(RequestCategory requestCategory);

    ResponseCategory toResponse(Category category);

    List<ResponseCategory> toResponseList(List<Category> categories);

}

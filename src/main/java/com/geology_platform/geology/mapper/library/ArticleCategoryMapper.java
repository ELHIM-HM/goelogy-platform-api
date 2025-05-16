package com.geology_platform.geology.mapper.library;

import com.geology_platform.geology.dto.request.collection.RequestCategory;
import com.geology_platform.geology.dto.request.library.RequestArticleCategory;
import com.geology_platform.geology.dto.response.collection.ResponseCategory;
import com.geology_platform.geology.entity.collection.Category;
import com.geology_platform.geology.entity.library.ArticleCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Mapper(componentModel = "spring")
public interface ArticleCategoryMapper {


    @Mapping(target = "articles", ignore = true)
    @Mapping(target = "id", ignore = true)
    ArticleCategory toEntity(RequestArticleCategory articleCategory);

    List<RequestArticleCategory> toResponseList(List<ArticleCategory> articleCategories);

}





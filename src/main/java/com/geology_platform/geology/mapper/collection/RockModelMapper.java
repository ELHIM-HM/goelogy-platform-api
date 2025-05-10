package com.geology_platform.geology.mapper.collection;

import com.geology_platform.geology.dto.request.collection.RequestCategory;
import com.geology_platform.geology.dto.request.collection.RequestRockModel;
import com.geology_platform.geology.dto.response.collection.ResponseCategory;
import com.geology_platform.geology.entity.collection.Category;
import com.geology_platform.geology.entity.collection.ModelItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/


@Mapper(componentModel = "spring")
public interface RockModelMapper {

    @Mapping(target = "modelId",ignore = true)
    @Mapping(target = "mineralProperties",ignore = true)
    @Mapping(target = "fossilProperties",ignore = true)
    ModelItem toEntity(RequestRockModel requestRockModel);

    RequestRockModel toResponse(RequestRockModel requestRockModel);

    List<RequestRockModel> toResponseList(List<ModelItem> modelItemList);

}

package com.geology_platform.geology.mapper.collection;

import com.geology_platform.geology.dto.request.collection.RequestMineralModel;
import com.geology_platform.geology.entity.collection.ModelItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Mapper(componentModel = "spring")
public interface MineralModelMapper {

    @Mapping(target = "modelId",ignore = true)
    @Mapping(target = "rockProperties",ignore = true)
    @Mapping(target = "fossilProperties",ignore = true)
    @Mapping(target = "mineraisProperties",ignore = true)
    ModelItem toEntity(RequestMineralModel requestMineralModel);

    RequestMineralModel toResponse(RequestMineralModel requestMineralModel);

    List<RequestMineralModel> toResponseList(List<ModelItem> modelItemList);

}

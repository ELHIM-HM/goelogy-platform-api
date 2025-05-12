package com.geology_platform.geology.mapper.collection;

import com.geology_platform.geology.dto.request.collection.RequestMineraisModel;
import com.geology_platform.geology.dto.request.collection.RequestMineralModel;
import com.geology_platform.geology.entity.collection.ModelItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/


@Mapper(componentModel = "spring")
public interface MineraisMapper {

    @Mapping(target = "modelId",ignore = true)
    @Mapping(target = "rockProperties",ignore = true)
    @Mapping(target = "fossilProperties",ignore = true)
    @Mapping(target = "mineralProperties",ignore = true)
    ModelItem toEntity(RequestMineraisModel requestMineraisModel);

    RequestMineraisModel toResponse(RequestMineraisModel requestMineralModel);

    List<RequestMineraisModel> toResponseList(List<ModelItem> modelItemList);

}
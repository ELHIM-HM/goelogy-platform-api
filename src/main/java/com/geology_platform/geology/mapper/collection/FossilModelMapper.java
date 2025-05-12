package com.geology_platform.geology.mapper.collection;

import com.geology_platform.geology.dto.request.collection.RequestFossilModel;
import com.geology_platform.geology.dto.request.collection.RequestRockModel;
import com.geology_platform.geology.entity.collection.ModelItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/



@Mapper(componentModel = "spring")
public interface FossilModelMapper {

    @Mapping(target = "modelId",ignore = true)
    @Mapping(target = "mineralProperties",ignore = true)
    @Mapping(target = "rockProperties",ignore = true)
    @Mapping(target = "mineraisProperties",ignore = true)
    ModelItem toEntity(RequestFossilModel requestFossilModel);

    RequestFossilModel toResponse(RequestFossilModel requestFossilModel);

    List<RequestFossilModel> toResponseList(List<ModelItem> modelItemList);

}
package com.geology_platform.geology.mapper.collection;

import com.geology_platform.geology.dto.response.collection.ResponseModelItem;

import com.geology_platform.geology.entity.collection.ModelItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelItemMapper {

    @Mapping(target = "rockProperties", ignore = true)
    @Mapping(target = "mineralProperties", ignore = true)
    @Mapping(target = "fossilProperties", ignore = true)
    ResponseModelItem toResponseModelItem(ModelItem entity, String type);


    default List<ResponseModelItem> toResponseModelItemList(List<ModelItem> entities, String type) {
        return entities.stream()
                .map(entity -> toResponseModelItem(entity, type))
                .toList();
    }

    @AfterMapping
    default void setSpecificProperties(ModelItem entity, String type, @MappingTarget ResponseModelItem response) {
        switch (type) {
            case "rock":
                response.setRockProperties(entity.getRockProperties());
                break;
            case "mineral":
                response.setMineralProperties(entity.getMineralProperties());
                break;
            case "fossil":
                response.setFossilProperties(entity.getFossilProperties());
                break;
            case "minerais":
                response.setMineraisProperties(entity.getMineraisProperties());
                break;
        }
    }


}
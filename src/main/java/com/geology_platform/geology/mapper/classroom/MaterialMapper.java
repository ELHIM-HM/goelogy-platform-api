package com.geology_platform.geology.mapper.classroom;

import com.geology_platform.geology.dto.classroom.AddMaterialDto;
import com.geology_platform.geology.entity.classroom.ClassMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author ELHIM Hamza
 **/

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "file", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ClassMaterial toEntity(AddMaterialDto materialDto);

}

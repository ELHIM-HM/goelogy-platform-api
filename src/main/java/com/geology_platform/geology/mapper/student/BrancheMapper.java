package com.geology_platform.geology.mapper.student;

import com.geology_platform.geology.dto.student.BrancheDto;
import com.geology_platform.geology.entity.student.Branche;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/


@Mapper(componentModel = "spring")
public interface BrancheMapper {


    @Mapping(target = "formation", ignore = true)
    Branche toEntity(BrancheDto formationDto);

    List<BrancheDto> toResponseList(List<Branche> formationList);


}

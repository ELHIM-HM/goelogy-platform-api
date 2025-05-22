package com.geology_platform.geology.mapper.student;

import com.geology_platform.geology.dto.student.FormationDto;
import com.geology_platform.geology.entity.student.Formation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Mapper(componentModel = "spring")
public interface FormationMapper {


    @Mapping(target = "brancheList", ignore = true)
    Formation toEntity(FormationDto formationDto);

    List<FormationDto> toResponseList(List<Formation> formationList);



}

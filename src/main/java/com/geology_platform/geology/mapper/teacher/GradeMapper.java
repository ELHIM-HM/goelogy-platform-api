package com.geology_platform.geology.mapper.teacher;

import com.geology_platform.geology.dto.teacher.GradeDto;
import com.geology_platform.geology.entity.teacher.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mapping(target = "teachers",ignore = true)
    Grade toEntity(GradeDto requestGrade);

    GradeDto toResponse(Grade grade);

    List<GradeDto> toResponseList(List<Grade> gradeList);

}

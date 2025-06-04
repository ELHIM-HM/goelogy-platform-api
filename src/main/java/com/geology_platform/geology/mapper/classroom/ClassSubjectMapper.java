package com.geology_platform.geology.mapper.classroom;

import com.geology_platform.geology.dto.classroom.ReadSubjectDto;
import com.geology_platform.geology.entity.classroom.ClassSubject;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author ELHIM Hamza
 **/

@Mapper(componentModel = "spring")
public interface ClassSubjectMapper {

    Set<ReadSubjectDto> toResponseList(Set<ClassSubject> classSubjectList);

}

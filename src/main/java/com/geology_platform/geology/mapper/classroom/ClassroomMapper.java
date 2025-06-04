package com.geology_platform.geology.mapper.classroom;

import com.geology_platform.geology.dto.classroom.ClassroomDto;
import com.geology_platform.geology.entity.classroom.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author ELHIM Hamza
 **/

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    @Mapping(target = "subjectList", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "studentList", ignore = true)
    @Mapping(target = "joinCode", ignore = true)
    @Mapping(target = "id", ignore = true)
    Classroom toEntity(ClassroomDto classroomDto);

}

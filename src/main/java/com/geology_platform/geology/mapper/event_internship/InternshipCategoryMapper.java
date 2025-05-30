package com.geology_platform.geology.mapper.event_internship;

import com.geology_platform.geology.dto.both.InternshipCategoryDTO;
import com.geology_platform.geology.entity.event_internship.InternshipCategory;


public class InternshipCategoryMapper {
    public static InternshipCategoryDTO toDTO(InternshipCategory category){
        if(category==null) return null;
        InternshipCategoryDTO dto = new InternshipCategoryDTO();
        dto.setLabel(category.getLabel());
        dto.setId(category.getId());
        return dto;
    }

    public static InternshipCategory toEntity(InternshipCategoryDTO dto){
        if(dto==null) return null;
        InternshipCategory entity = new InternshipCategory();
        entity.setLabel(dto.getLabel());
        return entity;
    }

}

package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.dto.both.InternshipCategoryDTO;

import java.util.List;

public interface IinternshipCategoryService {
    public InternshipCategoryDTO getCategoryById(long id) throws Exception;
    public List<InternshipCategoryDTO> getAllCategories();
    public InternshipCategoryDTO createCategory(InternshipCategoryDTO dto);
    public InternshipCategoryDTO updateCategory(long id,InternshipCategoryDTO dto) throws Exception;
    public void deleteCategory(long id) throws Exception;
}

package com.geology_platform.geology.service.event_internship.impl;

import com.geology_platform.geology.dto.both.InternshipCategoryDTO;
import com.geology_platform.geology.dto.both.InternshipDTO;
import com.geology_platform.geology.entity.event_internship.Internship;
import com.geology_platform.geology.entity.event_internship.InternshipCategory;
import com.geology_platform.geology.exception.event_internship.InternshipCategoryExistsAlready;
import com.geology_platform.geology.exception.event_internship.InternshipCategoryNotFoundException;
import com.geology_platform.geology.mapper.event_internship.InternshipCategoryMapper;
import com.geology_platform.geology.repository.event_internship.InternshipCategoryRepository;
import com.geology_platform.geology.repository.event_internship.InternshipRepository;
import com.geology_platform.geology.service.event_internship.interfaces.IinternshipCategoryService;
import com.geology_platform.geology.service.event_internship.interfaces.IinternshipService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class InternshipCategoryServiceImpl implements IinternshipCategoryService {


    private InternshipCategoryRepository categoryRepo;


    @Override
    public InternshipCategoryDTO getCategoryById(long id) throws Exception {
        InternshipCategory cat = categoryRepo.findById(id)
                .orElseThrow(()->new InternshipCategoryNotFoundException(
                        "Internship category could not be found"
                ));
        return InternshipCategoryMapper.toDTO(cat);
    }

    @Override
    public List<InternshipCategoryDTO> getAllCategories() {
        List<InternshipCategory> categories =  categoryRepo.findAll();
        return categories.stream()
                .map(InternshipCategoryMapper::toDTO)
                .collect(Collectors.toList());
    }


    public InternshipCategoryDTO createCategory(InternshipCategoryDTO dto){
        if(categoryRepo.existsByLabel(dto.getLabel())) throw new InternshipCategoryExistsAlready("this category exists already");
        System.out.println("=========="+dto.toString());
        InternshipCategory category =InternshipCategoryMapper.toEntity(dto);
        System.out.println("=========="+category.getLabel());
        categoryRepo.save(category);
        return InternshipCategoryMapper.toDTO(category);
    }

    public InternshipCategoryDTO updateCategory(long id,InternshipCategoryDTO dto) throws Exception{

        if(categoryRepo.existsByLabel(dto.getLabel())) throw new InternshipCategoryExistsAlready("this category exists already");

        System.out.println("==updating category===");
        InternshipCategory category = categoryRepo.findById(id)
                .orElseThrow(()->new InternshipCategoryNotFoundException(
                        "Internship category could not be found"
                ));

        System.out.println("========"+category.getLabel());
        category.setLabel(dto.getLabel());
        return InternshipCategoryMapper.toDTO(category);
    }

    public void deleteCategory(long id) throws Exception{
     InternshipCategory category = categoryRepo.findById(id)
             .orElseThrow(()->
                             new InternshipCategoryNotFoundException(
                                     "Internship category could not be found"
                             )
                     );
     System.out.println("========"+category.getLabel());
    categoryRepo.delete(category);
    }



}

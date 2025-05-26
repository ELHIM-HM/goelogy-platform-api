package com.geology_platform.geology.service.event_internship.impl;

import com.geology_platform.geology.dto.both.InternshipDTO;
import com.geology_platform.geology.entity.event_internship.ActivitySector;
import com.geology_platform.geology.entity.event_internship.Internship;
import com.geology_platform.geology.entity.event_internship.InternshipCategory;
import com.geology_platform.geology.exception.event_internship.InternshipCategoryNotFoundException;
import com.geology_platform.geology.exception.event_internship.InternshipNotFoundException;
import com.geology_platform.geology.exception.event_internship.SectorNotFoundException;
import com.geology_platform.geology.mapper.event_internship.InternshipCategoryMapper;
import com.geology_platform.geology.repository.event_internship.ActivitySectorRepository;
import com.geology_platform.geology.repository.event_internship.InternshipCategoryRepository;
import com.geology_platform.geology.repository.event_internship.InternshipRepository;
import com.geology_platform.geology.service.event_internship.interfaces.IinternshipService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class IntershipServiceImpl implements IinternshipService {


    private final InternshipRepository internshipRepo;
    private InternshipCategoryRepository categoRepo;
    private ActivitySectorRepository sectorRepo;


    @Override
    public InternshipDTO getInternshipById(long id){
        Internship internship = internshipRepo.findById(id).
                orElseThrow(()-> new InternshipNotFoundException("Internship could not be found"));
        InternshipDTO dto = new InternshipDTO();
        dto.setDescription(internship.getDescription());
        dto.setRemuneration(internship.getRemuneration());
        dto.setTitle(internship.getTitle());
        dto.setStartDate(internship.getStartDate());
        dto.setEndDate(internship.getEndDate());
        dto.setCategoryId(internship.getCategory().getId());
        dto.setSectorId(internship.getSector().getId());
        return dto;
    }

    @Override
    public List<InternshipDTO> getAllInternships(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Internship> internships = internshipRepo.findAll(pageable);
        return internships.stream()
                .map(internship -> {
                    InternshipDTO dto = new InternshipDTO();
                    dto.setDescription(internship.getDescription());
                    dto.setRemuneration(internship.getRemuneration());
                    dto.setTitle(internship.getTitle());
                    dto.setStartDate(internship.getStartDate());
                    dto.setEndDate(internship.getEndDate());
                    dto.setCategoryId(internship.getCategory().getId());
                    dto.setSectorId(internship.getSector().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<InternshipDTO> getInternshipsByCategory(long id, int page, int size) {

        if(!categoRepo.existsById(id)){
            throw new InternshipNotFoundException("this category couldn't be found");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Internship> internships = internshipRepo.findByCategoryId(id,pageable);
        return internships.stream()
                .map(internship -> {
                    InternshipDTO dto = new InternshipDTO();
                    dto.setDescription(internship.getDescription());
                    dto.setRemuneration(internship.getRemuneration());
                    dto.setTitle(internship.getTitle());
                    dto.setStartDate(internship.getStartDate());
                    dto.setEndDate(internship.getEndDate());
                    dto.setCategoryId(internship.getCategory().getId());
                    dto.setSectorId(internship.getSector().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<InternshipDTO> getInternshipsBySector(long id, int page, int size) {

        if(!sectorRepo.existsById(id)){
            throw new SectorNotFoundException("this sector couldn't be found");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Internship> internships = internshipRepo.findBySectorId(id,pageable);
        return internships.stream()
                .map(internship -> {
                    InternshipDTO dto = new InternshipDTO();
                    dto.setDescription(internship.getDescription());
                    dto.setRemuneration(internship.getRemuneration());
                    dto.setTitle(internship.getTitle());
                    dto.setStartDate(internship.getStartDate());
                    dto.setEndDate(internship.getEndDate());
                    dto.setCategoryId(internship.getCategory().getId());
                    dto.setSectorId(internship.getSector().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public InternshipDTO createInternship(InternshipDTO dto){
        InternshipCategory catego = categoRepo.findById(dto.getCategoryId())
                .orElseThrow(()->new InternshipCategoryNotFoundException("this category couldn't be found"));
        ActivitySector sector = sectorRepo.findById(dto.getSectorId())
                .orElseThrow(()->new SectorNotFoundException("this sector couldn' be found"));
        Internship internship = new Internship();
        internship.setCategory(catego);
        internship.setSector(sector);
        internship.setRemuneration(dto.getRemuneration());
        internship.setDescription(dto.getDescription());
        internship.setTitle(dto.getTitle());
        internship.setStartDate(dto.getStartDate());
        internship.setEndDate(dto.getEndDate());
        internshipRepo.save(internship);
        return dto;
    }

    public InternshipDTO updateInternship(long id,InternshipDTO dto){
        Internship internship = internshipRepo.findById(id)
                .orElseThrow(()->new InternshipNotFoundException("Internship could not be found"));
        System.out.println("internship found");
        InternshipCategory catego = categoRepo.findById(dto.getCategoryId())
                        .orElseThrow(()->new InternshipCategoryNotFoundException("this category couldn't be found"));
        System.out.println("categorydto to entity done");

        ActivitySector sector = sectorRepo.findById(dto.getSectorId())
                        .orElseThrow(()->new SectorNotFoundException("this sector couldn' be found"));


        internship.setCategory(catego);
        internship.setSector(sector);
        internship.setRemuneration(dto.getRemuneration());
        internship.setDescription(dto.getDescription());
        internship.setStartDate(dto.getStartDate());
        internship.setEndDate(dto.getEndDate());
        internship.setTitle(dto.getTitle());
        System.out.println("setting done");
        return dto;
    }

    public void deleteInternship(long id){
        System.out.println("deleting internship ....");
        Internship internship = internshipRepo.findById(id)
                .orElseThrow(()->new InternshipNotFoundException("Internship could not be found"));
        System.out.println("internship number "+id+" was found");
        internshipRepo.delete(internship);
    }

}

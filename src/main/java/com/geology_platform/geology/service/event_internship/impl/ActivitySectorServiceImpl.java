package com.geology_platform.geology.service.event_internship.impl;

import com.geology_platform.geology.dto.both.ActivitySectorDTO;
import com.geology_platform.geology.entity.event_internship.ActivitySector;
import com.geology_platform.geology.exception.event_internship.SectorExistsAlready;
import com.geology_platform.geology.exception.event_internship.SectorNotFoundException;
import com.geology_platform.geology.repository.event_internship.ActivitySectorRepository;
import com.geology_platform.geology.service.event_internship.interfaces.IActivitySectorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ActivitySectorServiceImpl implements IActivitySectorService {

    private ActivitySectorRepository sectorRepo;

    @Override
    public ActivitySectorDTO getActivitySector(long id) {
        ActivitySector sector =sectorRepo.findById(id)
                .orElseThrow(()->new SectorNotFoundException("this sector couldn't be found"));
        ActivitySectorDTO dto = new ActivitySectorDTO();
        dto.setLabel(sector.getLabel());
        return dto;
    }

    @Override
    public List<ActivitySectorDTO> getSectors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ActivitySector> sectorsPage = sectorRepo.findAll(pageable);

        return sectorsPage.getContent().stream()
                .map(sector -> new ActivitySectorDTO(sector.getLabel()))
                .toList();
    }

    @Override
    public ActivitySectorDTO createActivitySector(ActivitySectorDTO dto) {
        if(sectorRepo.existsByLabel(dto.getLabel())){
            throw new SectorExistsAlready("this sector exists already");
        }

        ActivitySector sector = new ActivitySector();
        sector.setLabel(dto.getLabel());
        sectorRepo.save(sector);
        return dto;
    }

    @Override
    public ActivitySectorDTO updateSector(long id, ActivitySectorDTO dto) {
        ActivitySector sector = sectorRepo.findById(id)
                .orElseThrow(()->new SectorNotFoundException("this sector couldn't be found"));
        if (sectorRepo.existsByLabel(dto.getLabel())){
            throw new SectorExistsAlready("this sector exists already");
        }

        sector.setLabel(dto.getLabel());
        return dto;

    }

    @Override
    public void deleteSector(long id) {
        ActivitySector sector = sectorRepo.findById(id)
                .orElseThrow(()->new SectorNotFoundException("this sector couldn't be found"));
        sectorRepo.delete(sector);
    }
}

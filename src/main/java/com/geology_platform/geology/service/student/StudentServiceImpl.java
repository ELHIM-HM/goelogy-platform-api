package com.geology_platform.geology.service.student;

import com.geology_platform.geology.dto.student.BrancheDto;
import com.geology_platform.geology.dto.student.FormationDto;
import com.geology_platform.geology.entity.student.Branche;
import com.geology_platform.geology.entity.student.Formation;
import com.geology_platform.geology.exception.global.ResourceNotFound;
import com.geology_platform.geology.mapper.student.BrancheMapper;
import com.geology_platform.geology.mapper.student.FormationMapper;
import com.geology_platform.geology.repository.student.BrancheRepo;
import com.geology_platform.geology.repository.student.FormationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ELHIM Hamza
 **/

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private FormationRepo formationRepo;
    private BrancheRepo brancheRepo;
    private FormationMapper formationMapper;
    private BrancheMapper brancheMapper;



    @Transactional
    @Override
    public void addFormation(FormationDto formationDto) {
        formationRepo.save(formationMapper.toEntity(formationDto));
    }

    @Transactional
    @Override
    public void addBranche(Long formationId, BrancheDto brancheDto) {
        Formation formation = formationRepo.findById(formationId).orElseThrow(()-> new ResourceNotFound("Formation not found",formationId));

        Branche branche = brancheMapper.toEntity(brancheDto);

        formation.addBranche(branche);

        brancheRepo.save(branche);

    }
}

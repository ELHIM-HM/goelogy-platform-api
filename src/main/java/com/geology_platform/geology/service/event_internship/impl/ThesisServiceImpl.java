package com.geology_platform.geology.service.event_internship.impl;

import com.geology_platform.geology.dto.both.ThesisDTO;
import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.Thesis;
import com.geology_platform.geology.entity.event_internship.ThesisStatus;
import com.geology_platform.geology.entity.teacher.Teacher;
import com.geology_platform.geology.exception.event_internship.ThesisNotFoundException;
import com.geology_platform.geology.repository.event_internship.IThesisRepository;
import com.geology_platform.geology.repository.teacher.TeacherRepo;
import com.geology_platform.geology.service.event_internship.interfaces.IThesisService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ThesisServiceImpl implements IThesisService {
    private IThesisRepository thesisRepo;
    private TeacherRepo teacherRepo;

    public List<ThesisDTO> getFilteredTheses(ThesisStatus status, Level level, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Thesis> result;

        if (status != null && level != null) {
            result = thesisRepo.findByStatusAndLevel(status, level, pageable);
        } else if (status != null) {
            result = thesisRepo.findByStatus(status, pageable);
        } else if (level != null) {
            result = thesisRepo.findByLevel(level, pageable);
        } else {
            result = thesisRepo.findAll(pageable);
        }

        return result.stream().map(
                thesis -> {
                    ThesisDTO dto = new ThesisDTO();
                    dto.setId(thesis.getId());
                    dto.setSubject(thesis.getSubject());
                    dto.setDescription(thesis.getDescription());
                    dto.setCreatedAt(thesis.getCreatedAt());
                    dto.setLevel(thesis.getLevel().name());
                    dto.setStatus(thesis.getStatus().name());
                    dto.setSupervisorId(thesis.getSupervisor().getId());
                    return dto;}
        ).collect(Collectors.toList());
    }





    @Override
    public List<ThesisDTO> getAllThesis(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Thesis> theses = thesisRepo.findAll(pageable);
        return theses.stream()
                .map(thesis -> {
                    ThesisDTO dto = new ThesisDTO();
                    dto.setId(thesis.getId());
                    dto.setSubject(thesis.getSubject());
                    dto.setDescription(thesis.getDescription());
                    dto.setCreatedAt(thesis.getCreatedAt());
                    dto.setLevel(thesis.getLevel().name());
                    dto.setStatus(thesis.getStatus().name());
                    dto.setSupervisorId(thesis.getSupervisor().getId());
                    return dto;
                }).collect(Collectors.toList());


    }

    @Override
    public ThesisDTO getThesis(long id) {
        Thesis thesis = thesisRepo.findById(id)
                .orElseThrow(()->new ThesisNotFoundException("this thesis couldn't be found"));
        ThesisDTO dto = new ThesisDTO();
        dto.setId(thesis.getId());
        dto.setSubject(thesis.getSubject());
        dto.setDescription(thesis.getDescription());
        dto.setCreatedAt(thesis.getCreatedAt());
        dto.setLevel(thesis.getLevel().name());
        dto.setStatus(thesis.getStatus().name());
        dto.setSupervisorId(thesis.getSupervisor().getId());
        return dto;
    }

    @Override
    public List<ThesisDTO> getThesisByStatus(ThesisStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Thesis> theses = thesisRepo.findByStatus(status,pageable);
        return theses.stream()
                .map(thesis -> {
                    ThesisDTO dto = new ThesisDTO();
                    dto.setId(thesis.getId());
                    dto.setSubject(thesis.getSubject());
                    dto.setDescription(thesis.getDescription());
                    dto.setCreatedAt(thesis.getCreatedAt());
                    dto.setLevel(thesis.getLevel().name());
                    dto.setStatus(thesis.getStatus().name());
                    dto.setSupervisorId(thesis.getSupervisor().getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ThesisDTO> getThesisByLevel(Level level, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Thesis> theses = thesisRepo.findByLevel(level,pageable);
        return theses.stream()
                .map(thesis -> {
                    ThesisDTO dto = new ThesisDTO();
                    dto.setId(thesis.getId());
                    dto.setSubject(thesis.getSubject());
                    dto.setDescription(thesis.getDescription());
                    dto.setCreatedAt(thesis.getCreatedAt());
                    dto.setLevel(thesis.getLevel().name());
                    dto.setStatus(thesis.getStatus().name());
                    dto.setSupervisorId(thesis.getSupervisor().getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ThesisDTO> getThesisBySupervisor(long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Thesis> theses = thesisRepo.findBySupervisorId(id,pageable);
        return theses.stream()
                .map(thesis -> {
                    ThesisDTO dto = new ThesisDTO();
                    dto.setId(thesis.getId());
                    dto.setSubject(thesis.getSubject());
                    dto.setDescription(thesis.getDescription());
                    dto.setCreatedAt(thesis.getCreatedAt());
                    dto.setLevel(thesis.getLevel().name());
                    dto.setStatus(thesis.getStatus().name());
                    dto.setSupervisorId(thesis.getSupervisor().getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public ThesisDTO createThesis(ThesisDTO dto) {
        Teacher teacher = teacherRepo.findById(dto.getSupervisorId())
                .orElseThrow(()->new ThesisNotFoundException("teacher couldn't be found"));
        Thesis thesis = new Thesis();
        thesis.setSupervisor(teacher);
        thesis.setDescription(dto.getDescription());
        thesis.setSubject(dto.getSubject());
        thesis.setLevel(Level.fromString(dto.getLevel()));
        System.out.println("levle recu dans post:"+dto.getLevel());
        thesis.setStatus(ThesisStatus.fromString(dto.getStatus()));
        thesisRepo.save(thesis);
        dto.setId(thesis.getId());
        dto.setStatus(thesis.getStatus().name());
        dto.setLevel(thesis.getLevel().name());
        dto.setCreatedAt(thesis.getCreatedAt());
        return dto;
    }

    @Override
    public ThesisDTO updateThesis(long id,ThesisDTO dto) {
        Teacher teacher = teacherRepo.findById(dto.getSupervisorId())
                .orElseThrow(()->new ThesisNotFoundException("teacher couldn't be found"));

        Thesis thesis = thesisRepo.findById(id)
                .orElseThrow(()->new ThesisNotFoundException("thesis couldn't be found"));
        thesis.setSupervisor(teacher);
        thesis.setDescription(dto.getDescription());
        thesis.setSubject(dto.getSubject());
        thesis.setLevel(Level.fromString(dto.getLevel()));
        System.out.println("levle recu dans put:"+dto.getLevel());
        thesis.setStatus(ThesisStatus.fromString(dto.getStatus()));
        System.out.println("statut recu dans put:"+dto.getStatus());
        dto.setId(thesis.getId());
        dto.setStatus(thesis.getStatus().name());
        dto.setLevel(thesis.getLevel().name());
        dto.setCreatedAt(thesis.getCreatedAt());
        return dto;
    }

    @Override
    public void deleteThesis(long id) {
        Thesis thesis = thesisRepo.findById(id)
                .orElseThrow(()->new ThesisNotFoundException("thesis couldn't be found"));
        thesisRepo.delete(thesis);
    }
}

package com.geology_platform.geology.service.event_internship.impl;

import com.geology_platform.geology.dto.both.JobDTO;
import com.geology_platform.geology.entity.event_internship.*;
import com.geology_platform.geology.exception.event_internship.EventCategoryNotFoundException;
import com.geology_platform.geology.exception.event_internship.InternshipCategoryNotFoundException;
import com.geology_platform.geology.exception.event_internship.JobNotFoundException;
import com.geology_platform.geology.exception.event_internship.SectorNotFoundException;
import com.geology_platform.geology.repository.event_internship.ActivitySectorRepository;
import com.geology_platform.geology.repository.event_internship.IJobRepository;
import com.geology_platform.geology.repository.event_internship.InternshipCategoryRepository;
import com.geology_platform.geology.service.event_internship.interfaces.IJobService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class JobServiceImpl implements IJobService {

    private IJobRepository jobRepo;
    private ActivitySectorRepository sectorRepo;
    private InternshipCategoryRepository categoryRepo;
    @Override
    public List<JobDTO> getAllJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobs = jobRepo.findAll(pageable);
        System.out.println("7tal lhna mzn f getAlljobs");
        return jobs.stream()
                .map(job -> {
                    JobDTO dto = new JobDTO();
                    dto.setId(job.getId());
                    dto.setDescription(job.getDescription());
                    dto.setCity(job.getCity());
                    dto.setCountry(job.getCountry());
                    dto.setTitle(job.getTitle());
                    dto.setRecruiter(job.getRecruiter());
                    dto.setContractType(job.getContractType());
                    dto.setCategoryId(job.getCategory().getId());
                    dto.setSectorId(job.getSector().getId());
                    dto.setRecruiterPhoneNumber(job.getRecruiterPhoneNumber());
                    dto.setRecruiterEmail(job.getRecruiterEmail());
                    dto.setRequiredExperienceDurationInMonths(job.getRequiredExperienceDuration());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<JobDTO> getJobsByCategory(long id, int page, int size) {
        InternshipCategory category = categoryRepo.findById(id)
                .orElseThrow(()->new InternshipCategoryNotFoundException("this category couldn't be found"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobs = jobRepo.findByCategoryId(id,pageable);
      return jobs.stream()
              .map((job)->{
                  JobDTO dto = new JobDTO();
                  dto.setId(job.getId());
                  dto.setDescription(job.getDescription());
                  dto.setCity(job.getCity());
                  dto.setCountry(job.getCountry());
                  dto.setTitle(job.getTitle());
                  dto.setRecruiter(job.getRecruiter());
                  dto.setContractType(job.getContractType());
                  dto.setCategoryId(job.getCategory().getId());
                  dto.setSectorId(job.getSector().getId());
                  dto.setRecruiterPhoneNumber(job.getRecruiterPhoneNumber());
                  dto.setRecruiterEmail(job.getRecruiterEmail());
                  dto.setRequiredExperienceDurationInMonths(job.getRequiredExperienceDuration());
                  return dto;
              }).collect(Collectors.toList());

    }

    @Override
    public List<JobDTO> getJobsBySector(long id, int page, int size) {
        ActivitySector sector = sectorRepo.findById(id)
                .orElseThrow(()-> new SectorNotFoundException("this sector couldn't be found"));
        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobs = jobRepo.findBySectorId(id,pageable);
        return jobs.stream()
                .map((job)->{
                    JobDTO dto = new JobDTO();
                    dto.setId(job.getId());
                    dto.setDescription(job.getDescription());
                    dto.setCity(job.getCity());
                    dto.setCountry(job.getCountry());
                    dto.setTitle(job.getTitle());
                    dto.setRecruiter(job.getRecruiter());
                    dto.setContractType(job.getContractType());
                    dto.setCategoryId(job.getCategory().getId());
                    dto.setSectorId(job.getSector().getId());
                    dto.setRecruiterPhoneNumber(job.getRecruiterPhoneNumber());
                    dto.setRecruiterEmail(job.getRecruiterEmail());
                    dto.setRequiredExperienceDurationInMonths(job.getRequiredExperienceDuration());
                    return dto;
                }).collect(Collectors.toList());


    }

    @Override
    public JobDTO getJob(long id) {
       Job job= jobRepo.findById(id)
               .orElseThrow(()->new JobNotFoundException("Job couldn't be found"));
       JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setDescription(job.getDescription());
        dto.setCity(job.getCity());
        dto.setCountry(job.getCountry());
        dto.setTitle(job.getTitle());
        dto.setRecruiter(job.getRecruiter());
        dto.setContractType(job.getContractType());
        dto.setCategoryId(job.getCategory().getId());
        dto.setSectorId(job.getSector().getId());
        dto.setRecruiterPhoneNumber(job.getRecruiterPhoneNumber());
        dto.setRecruiterEmail(job.getRecruiterEmail());
        dto.setRequiredExperienceDurationInMonths(job.getRequiredExperienceDuration());
        return dto;
    }

    @Override
    public JobDTO createJob(JobDTO dto) {
        InternshipCategory category =categoryRepo.findById((long) dto.getCategoryId())
                .orElseThrow(()-> new EventCategoryNotFoundException("category couldn't be found"));
        ActivitySector sector = sectorRepo.findById((long)dto.getSectorId())
                .orElseThrow(()->new SectorNotFoundException("this sector couldn't be found"));
        Job job = new Job();
        job.setCity(dto.getCity());
        job.setCountry(dto.getCountry());
        job.setRecruiter(dto.getRecruiter());
        job.setContractType(dto.getContractType());
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setRequiredExperienceDuration(dto.getRequiredExperienceDurationInMonths());
        job.setRemote(dto.isRemote());
        job.setRecruiterPhoneNumber(dto.getRecruiterPhoneNumber());
        job.setRecruiterEmail(dto.getRecruiterEmail());
        job.setRequiredExperienceDuration(dto.getRequiredExperienceDurationInMonths());
        job.setCategory(category);
        job.setSector(sector);
        jobRepo.save(job);
        dto.setId(job.getId());
        return dto;

    }

    @Override
    public JobDTO updateJob( long id, JobDTO dto) {
        Job job = jobRepo.findById(id).orElseThrow(()->new JobNotFoundException("job couldn't be found"));
        InternshipCategory category =categoryRepo.findById((long) dto.getCategoryId())
                .orElseThrow(()-> new EventCategoryNotFoundException("category couldn't be found"));
        ActivitySector sector = sectorRepo.findById((long)dto.getSectorId())
                .orElseThrow(()->new SectorNotFoundException("this sector couldn't be found"));
        job.setCity(dto.getCity());
        job.setCountry(dto.getCountry());
        job.setRecruiter(dto.getRecruiter());
        job.setContractType(dto.getContractType());
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setRequiredExperienceDuration(dto.getRequiredExperienceDurationInMonths());
        job.setRemote(dto.isRemote());
        job.setRecruiterEmail(dto.getRecruiterEmail());
        job.setRecruiterPhoneNumber(dto.getRecruiterPhoneNumber());
        job.setRequiredExperienceDuration(dto.getRequiredExperienceDurationInMonths());
        job.setCategory(category);
        job.setSector(sector);
        dto.setId(job.getId());
        return dto;
    }

    @Override
    public void deleteJob(long id) {
        Job job = jobRepo.findById(id)
                .orElseThrow(()->new JobNotFoundException("job couldn't be found"));
        jobRepo.delete(job);
    }
}

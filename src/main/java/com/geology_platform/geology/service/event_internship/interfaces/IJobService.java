package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.dto.both.EventDTO;
import com.geology_platform.geology.dto.both.JobDTO;

import java.util.List;

public interface IJobService {
    public List<JobDTO> getAllJobs(int page, int size);
    public List<JobDTO>  getJobsByCategory(long id,int page,int size);
    public List<JobDTO> getJobsBySector(long id,int page,int size);
    public JobDTO getJob(long id);
    public JobDTO createJob(JobDTO dto);
    public JobDTO updateJob(long id,JobDTO dto);
    public void deleteJob(long id);

}

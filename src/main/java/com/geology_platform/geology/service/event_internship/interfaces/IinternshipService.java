package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.dto.both.InternshipDTO;

import java.util.List;

public interface IinternshipService {
    public InternshipDTO getInternshipById(long id);
    public List<InternshipDTO> getAllInternships(int page,int size);
    public List<InternshipDTO> getInternshipsByCategory(long id,int page,int size);
    public List<InternshipDTO> getInternshipsBySector(long id,int page,int size);
    public InternshipDTO createInternship(InternshipDTO dto);
    public InternshipDTO updateInternship(long id,InternshipDTO dto);
    public void deleteInternship(long id) ;
}

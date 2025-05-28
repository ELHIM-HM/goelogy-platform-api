package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.dto.both.ThesisDTO;
import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.ThesisStatus;


import java.util.List;

public interface IThesisService {

    public List<ThesisDTO> getAllThesis(int page,int size);
    public ThesisDTO getThesis(long id);
    public List<ThesisDTO> getThesisByStatus(ThesisStatus status, int page, int size);
    public List<ThesisDTO> getThesisByLevel(Level level, int page, int size);
    public List<ThesisDTO> getThesisBySupervisor(long id,int page,int size);
    public ThesisDTO createThesis(ThesisDTO dto);
    public ThesisDTO updateThesis(long id,ThesisDTO dto);
    public void deleteThesis(long id);

}

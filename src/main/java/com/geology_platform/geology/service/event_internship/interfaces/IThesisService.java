package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.dto.both.ThesisDTO;
import com.geology_platform.geology.dto.both.ThesisRequestDTO;
import com.geology_platform.geology.entity.event_internship.Level;
import com.geology_platform.geology.entity.event_internship.Thesis;
import com.geology_platform.geology.entity.event_internship.ThesisStatus;


import java.util.List;

public interface IThesisService {

    public List<ThesisDTO> getAllThesis(int page, int size);
    public ThesisDTO getThesis(long id);
    public ThesisDTO createThesis(ThesisRequestDTO dto);
    public ThesisDTO updateThesis(long id,ThesisRequestDTO dto);
    public void deleteThesis(long id);

}

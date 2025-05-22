package com.geology_platform.geology.service.student;

import com.geology_platform.geology.dto.student.BrancheDto;
import com.geology_platform.geology.dto.student.FormationDto;

/**
 * @author ELHIM Hamza
 **/
public interface StudentService {
    void addFormation(FormationDto formationDto);

    void addBranche(Long formationId, BrancheDto brancheDto);


}

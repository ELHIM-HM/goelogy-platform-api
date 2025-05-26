package com.geology_platform.geology.service.event_internship.interfaces;

import com.geology_platform.geology.dto.both.ActivitySectorDTO;

import java.util.List;

public interface IActivitySectorService {
    public ActivitySectorDTO getActivitySector(long id);
    public List<ActivitySectorDTO> getSectors(int page,int size);
    public ActivitySectorDTO createActivitySector(ActivitySectorDTO sector);
    public ActivitySectorDTO updateSector(long id,ActivitySectorDTO sector);
    public void deleteSector(long id);

}

package com.geology_platform.geology.dto.response.collection;

import com.geology_platform.geology.entity.FileData;
import com.geology_platform.geology.entity.collection.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author ELHIM Hamza
 **/


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModelItem {

    private Long modelId;
    private String inventoryNumber;
    private String modelURL;
    private String categoryName; // "rock", "mineral", or "fossil"
    private List<OtherInfos> otherInfos;



    private RockProperties rockProperties;
    private MineralProperties mineralProperties;
    private FossilProperties fossilProperties;
    private MineraisProperties mineraisProperties;

    private FileData model3d;
    private FileData video;

}

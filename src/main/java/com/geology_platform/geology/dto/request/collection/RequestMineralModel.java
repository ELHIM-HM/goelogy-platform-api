package com.geology_platform.geology.dto.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.FileData;
import com.geology_platform.geology.entity.collection.MineralProperties;
import com.geology_platform.geology.entity.collection.OtherInfos;
import com.geology_platform.geology.entity.collection.RockProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Getter
@Setter
@AllArgsConstructor
public class RequestMineralModel {

    private String inventoryNumber;


    private Long subcategoryId;

    @JsonIgnore
    private String categoryName;

    private List<OtherInfos> otherInfos ;

    private MineralProperties mineralProperties;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private FileData model3d;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private FileData video;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FileData> images;
}
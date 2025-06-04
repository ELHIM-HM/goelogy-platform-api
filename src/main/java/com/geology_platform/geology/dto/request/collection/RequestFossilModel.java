package com.geology_platform.geology.dto.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.geology_platform.geology.entity.FileData;
import com.geology_platform.geology.entity.collection.FossilProperties;
import com.geology_platform.geology.entity.collection.OtherInfos;
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
public class RequestFossilModel {

    private String inventoryNumber;



    private Long subcategoryId;

    private List<OtherInfos> otherInfos ;

    @JsonIgnore
    private String categoryName;

    private FossilProperties fossilProperties;;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private FileData model3d;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private FileData video;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FileData> images;
}
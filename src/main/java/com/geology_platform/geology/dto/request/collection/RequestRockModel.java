package com.geology_platform.geology.dto.request.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geology_platform.geology.entity.collection.MineralProperties;
import com.geology_platform.geology.entity.collection.OtherInfos;
import com.geology_platform.geology.entity.collection.RockProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RequestRockModel {

    private String inventoryNumber;

    private String modelURL;

    private Long subcategoryId;

    @JsonIgnore
    private String categoryName;

    private List<OtherInfos> otherInfos ;

    private RockProperties rockProperties ;



}

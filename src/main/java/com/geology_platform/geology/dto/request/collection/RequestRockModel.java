package com.geology_platform.geology.dto.request.collection;

import com.geology_platform.geology.entity.collection.MineralProperties;
import com.geology_platform.geology.entity.collection.OtherInfos;
import com.geology_platform.geology.entity.collection.RockProperties;
import jakarta.persistence.*;
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
public class RequestRockModel {

    private String inventoryNumber;

    private String modelURL;

    private Long subcategoryId;

    private List<OtherInfos> otherInfos ;

    private RockProperties rockProperties ;
}

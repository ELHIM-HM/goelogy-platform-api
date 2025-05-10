package com.geology_platform.geology.dto.request.collection;

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

    private String modelURL;

    private List<OtherInfos> otherInfos ;

    private FossilProperties fossilProperties;;
}
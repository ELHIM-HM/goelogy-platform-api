package com.geology_platform.geology.entity.collection;

import com.geology_platform.geology.entity.FileData;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ELHIM Hamza
 **/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "model_item")
@ToString
public class ModelItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "inventory_number")
    private String inventoryNumber;

    @Column(name = "category_name")
    private String categoryName;


    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private List<OtherInfos> otherInfos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id_mineral")
    private MineralProperties mineralProperties;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id_rock")
    private RockProperties rockProperties;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id_fossil")
    private FossilProperties fossilProperties;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id_minerais")
    private MineraisProperties mineraisProperties;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private FileData model3d;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "video_id")
    private FileData video;



    public void addOtherInfos(String name,String value){
        otherInfos.add(new OtherInfos(name,value));
    }

    public void removeOtherInfos(OtherInfos other){
        otherInfos.remove(other);
    }



}

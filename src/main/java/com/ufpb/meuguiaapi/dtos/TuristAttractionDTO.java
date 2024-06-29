package com.ufpb.meuguiaapi.dtos;

import com.ufpb.meuguiaapi.domain.Attraction;
import com.ufpb.meuguiaapi.domain.AttractionType;
import com.ufpb.meuguiaapi.domain.MoreInfoLink;
import com.ufpb.meuguiaapi.domain.TurismSegmentation;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
public class TuristAttractionDTO {

    private Long id;

    private String name;

    private String description;

    private String map_link;

    private String city;

    private String state;

    private String image_link;

    private List<TurismSegmentation> segmentations;

    private AttractionType attractionTypes;

    private List<MoreInfoLink> moreInfoLinkList;

    public TuristAttractionDTO() {
    }

    public TuristAttractionDTO(Attraction obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.description = obj.getDescription();
        this.map_link = obj.getMap_link();
        this.city = obj.getCity();
        this.state = obj.getState();
        this.image_link = obj.getImage_link();
        this.segmentations = obj.getSegmentations();
        this.attractionTypes = obj.getAttractionTypes();
        this.moreInfoLinkList = obj.getMoreInfoLinkList();
    }

    public TuristAttractionDTO(Long id, String name, String description, String map_link, String city, String state,
                               String image_link, List<TurismSegmentation> segmentations, AttractionType attractionTypes, List<MoreInfoLink> moreInfoLinkList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.map_link = map_link;
        this.city = city;
        this.state = state;
        this.image_link = image_link;
        this.segmentations = segmentations;
        this.attractionTypes = attractionTypes;
        this.moreInfoLinkList = moreInfoLinkList;
    }
}

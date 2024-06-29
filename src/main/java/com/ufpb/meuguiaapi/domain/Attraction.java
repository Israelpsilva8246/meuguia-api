package com.ufpb.meuguiaapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 100000, nullable = false)
    private String description;

    @Column(length = 200, nullable = false)
    private String map_link;

    @Column(length = 200, nullable = false)
    private String city;

    @Column(length = 200, nullable = false)
    private String state;

    @Column(length = 500, nullable = false)
    private String image_link;

    @OneToMany
    @JoinColumn(name = "attraction_id")
    private List<TurismSegmentation> segmentations;

    @OneToOne
    private AttractionType attractionTypes;

    @OneToMany
    @JoinColumn(name = "attraction_id")
    private List<MoreInfoLink> moreInfoLinkList;

    public Attraction() {
    }

    public Attraction(Long id, String name, String description, String mapLink, String city, String state,
                      String imageLink, List<TurismSegmentation> segmentations, AttractionType attractionTypes, List<MoreInfoLink> moreInfoLinkList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.map_link = mapLink;
        this.city = city;
        this.state = state;
        this.image_link = imageLink;
        this.segmentations = segmentations;
        this.attractionTypes = attractionTypes;
        this.moreInfoLinkList = moreInfoLinkList;
    }
}

package com.ufpb.meuguiaapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column(length = 300, nullable = false)
    private String fonte;

    @ManyToMany
    @JoinTable(
            name = "attraction_segmentation",
            joinColumns = @JoinColumn(name = "attraction_id"),
            inverseJoinColumns = @JoinColumn(name = "segmentation_id")
    )
    private List<TouristSegmentation> segmentations = new ArrayList<>();

    @OneToOne
    @JoinTable(
            name = "attraction_attractiontype",
            joinColumns = @JoinColumn(name = "attraction_id"),
            inverseJoinColumns = @JoinColumn(name = "attractiontype_id")
    )
    private AttractionType attractionTypes;

    @OneToMany
    @JoinColumn(name = "attraction_id")
    private List<MoreInfoLink> moreInfoLinkList = new ArrayList<>();

    public Attraction() {
    }

    public Attraction(Long id, String name, String description, String map_link, String city, String state, String image_link, String fonte) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.map_link = map_link;
        this.city = city;
        this.state = state;
        this.image_link = image_link;
        this.fonte = fonte;
    }
}

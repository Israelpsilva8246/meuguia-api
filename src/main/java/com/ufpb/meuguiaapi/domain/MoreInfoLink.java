package com.ufpb.meuguiaapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MoreInfoLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String link;

    @Column(length = 200, nullable = false)
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;

    public MoreInfoLink() {
    }

    public MoreInfoLink(Long id, String link, String description, Attraction attraction) {
        this.id = id;
        this.link = link;
        this.description = description;
        this.attraction = attraction;
    }
}

package com.ufpb.meuguiaapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AttractionType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;

    public AttractionType() {
    }

    public AttractionType(Long id, String name, String description, Attraction attraction) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attraction = attraction;
    }
}
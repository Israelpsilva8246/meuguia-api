package com.ufpb.meuguiaapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TurismSegmentation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;

    public TurismSegmentation() {
    }

    public TurismSegmentation(Long id, String name, String description, Attraction attraction) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attraction = attraction;
    }
}

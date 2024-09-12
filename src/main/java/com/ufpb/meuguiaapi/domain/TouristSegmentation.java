package com.ufpb.meuguiaapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TouristSegmentation {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tourist_seq")
//    @SequenceGenerator(name = "tourist_seq", sequenceName = "tourist_sequence", allocationSize = 11)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    public TouristSegmentation() {
    }

    public TouristSegmentation(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

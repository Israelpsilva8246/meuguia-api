package com.ufpb.meuguiaapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AttractionType {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attractiontype_seq")
//    @SequenceGenerator(name = "attractiontype_seq", sequenceName = "attractiontype_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    public AttractionType() {
    }

    public AttractionType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

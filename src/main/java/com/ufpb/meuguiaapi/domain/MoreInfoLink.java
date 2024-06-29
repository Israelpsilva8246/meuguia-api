package com.ufpb.meuguiaapi.domain;

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

    public MoreInfoLink() {
    }

    public MoreInfoLink(Long id, String link, String description) {
        this.id = id;
        this.link = link;
        this.description = description;
    }
}

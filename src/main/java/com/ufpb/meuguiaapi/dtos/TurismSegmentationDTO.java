package com.ufpb.meuguiaapi.dtos;

import com.ufpb.meuguiaapi.domain.TouristSegmentation;
import lombok.Data;

@Data
public class TurismSegmentationDTO {

    private Long id;

    private String name;

    private String description;

    public TurismSegmentationDTO() {
    }

    public TurismSegmentationDTO(TouristSegmentation obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.description = obj.getDescription();
    }
}

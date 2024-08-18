package com.ufpb.meuguiaapi.service;

import com.ufpb.meuguiaapi.domain.Attraction;
import com.ufpb.meuguiaapi.dtos.TuristAttractionDTO;
import com.ufpb.meuguiaapi.exception.ObjectNotFoundException;
import com.ufpb.meuguiaapi.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {

    @Autowired
    private AttractionRepository turistAttractionRepository;

    public Attraction create(Attraction obj) {
        obj.setId(null);
        return turistAttractionRepository.save(obj);
    }

    public Attraction findById(Long id) {
        Optional<Attraction> obj = turistAttractionRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Attraction.class.getName()));
    }

    public List<Attraction> findAll() {
        return turistAttractionRepository.findAll();
    }

    public List<Attraction> findByName(String name) {
        return turistAttractionRepository.findAllByName(name);
    }

    public List<Attraction> findByCity(String city) {
        return turistAttractionRepository.findAllByCity(city);
    }

    public List<Attraction> findBySegmentations(String segmentations) {
        return turistAttractionRepository.findAllBySegmentationName(segmentations);
    }

    public List<Attraction> findByType(String attractionTypes) {
        return turistAttractionRepository.findAllByType(attractionTypes);
    }

    public void delete(Long id) {
        findById(id);
        turistAttractionRepository.deleteById(id);
    }

    public Attraction update(Long id, TuristAttractionDTO objDto) {
        Attraction obj = findById(id);
        obj.setName(objDto.getName());
        obj.setDescription(objDto.getDescription());
        obj.setMap_link(objDto.getMap_link());
        obj.setCity(objDto.getCity());
        obj.setState(objDto.getState());
        obj.setImage_link(objDto.getImage_link());
        obj.setFonte(objDto.getFonte());
        return turistAttractionRepository.save(obj);
    }
}

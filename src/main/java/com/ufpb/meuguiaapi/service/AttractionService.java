package com.ufpb.meuguiaapi.service;

import com.ufpb.meuguiaapi.domain.Attraction;
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
}

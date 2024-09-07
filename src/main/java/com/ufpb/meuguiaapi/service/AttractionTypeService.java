package com.ufpb.meuguiaapi.service;

import com.ufpb.meuguiaapi.domain.Attraction;
import com.ufpb.meuguiaapi.domain.AttractionType;
import com.ufpb.meuguiaapi.domain.MoreInfoLink;
import com.ufpb.meuguiaapi.exception.ObjectNotFoundException;
import com.ufpb.meuguiaapi.repository.AttractionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttractionTypeService {

    @Autowired
    private AttractionTypeRepository attractionTypeRepository;

    @Autowired
    private AttractionService attractionService;

    public AttractionType create(AttractionType obj) {
        obj.setId(null);
        return attractionTypeRepository.save(obj);
    }

    public void findById(Long id) {
        Optional<AttractionType> obj = attractionTypeRepository.findById(id);
        obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Attraction.class.getName()));
    }

    public List<AttractionType> findAll() {
        return attractionTypeRepository.findAll();
    }

    public void delete(Long id) {
        findById(id);
        attractionTypeRepository.deleteById(id);
    }
}

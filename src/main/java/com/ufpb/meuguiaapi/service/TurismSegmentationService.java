package com.ufpb.meuguiaapi.service;

import com.ufpb.meuguiaapi.domain.TurismSegmentation;
import com.ufpb.meuguiaapi.exception.ObjectNotFoundException;
import com.ufpb.meuguiaapi.repository.TurismSegmentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurismSegmentationService {

    @Autowired
    private TurismSegmentationRepository turismSegmentationRepository;

    public TurismSegmentation findById(Long id) {
        Optional<TurismSegmentation> obj = turismSegmentationRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo " + TurismSegmentation.class.getName()));
    }

    public List<TurismSegmentation> findAll() {
        return turismSegmentationRepository.findAll();
    }

    public TurismSegmentation create(TurismSegmentation obj) {
        obj.setId(null);
        return turismSegmentationRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        turismSegmentationRepository.deleteById(id);
    }
}

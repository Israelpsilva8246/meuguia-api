package com.ufpb.meuguiaapi.service;

import com.ufpb.meuguiaapi.domain.TouristSegmentation;
import com.ufpb.meuguiaapi.exception.ObjectNotFoundException;
import com.ufpb.meuguiaapi.repository.TurismSegmentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TurismSegmentationService {

    @Autowired
    private TurismSegmentationRepository turismSegmentationRepository;

    @Autowired
    private AttractionService attractionService;

    public TouristSegmentation findById(Long id) {
        Optional<TouristSegmentation> obj = turismSegmentationRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo " + TouristSegmentation.class.getName()));
    }

    public List<TouristSegmentation> findAll() {
        List<TouristSegmentation> segmentations = turismSegmentationRepository.findAll();

        // Filtra para manter apenas as segmentações com nomes únicos
        return new ArrayList<>(segmentations.stream()
                .collect(Collectors.toMap(
                        TouristSegmentation::getName,  // Usa o nome como chave
                        Function.identity(),          // Usa o próprio objeto TurismSegmentation como valor
                        (existing, replacement) -> existing // Em caso de duplicata, mantém o existente
                ))
                .values());
    }

    public TouristSegmentation create(TouristSegmentation obj) {
        obj.setId(null);
        return turismSegmentationRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        turismSegmentationRepository.deleteById(id);
    }
}

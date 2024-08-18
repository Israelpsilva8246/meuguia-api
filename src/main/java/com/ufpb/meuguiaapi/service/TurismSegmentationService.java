package com.ufpb.meuguiaapi.service;

import com.ufpb.meuguiaapi.domain.Attraction;
import com.ufpb.meuguiaapi.domain.TurismSegmentation;
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

    public TurismSegmentation findById(Long id) {
        Optional<TurismSegmentation> obj = turismSegmentationRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo " + TurismSegmentation.class.getName()));
    }

    public List<TurismSegmentation> findAll() {
        List<TurismSegmentation> segmentations = turismSegmentationRepository.findAll();

        // Filtra para manter apenas as segmentações com nomes únicos
        return new ArrayList<>(segmentations.stream()
                .collect(Collectors.toMap(
                        TurismSegmentation::getName,  // Usa o nome como chave
                        Function.identity(),          // Usa o próprio objeto TurismSegmentation como valor
                        (existing, replacement) -> existing // Em caso de duplicata, mantém o existente
                ))
                .values());
    }

    public TurismSegmentation create(Long id_att, TurismSegmentation obj) {
        obj.setId(null);
        Attraction att = attractionService.findById(id_att);
        obj.setAttraction(att);
        return turismSegmentationRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        turismSegmentationRepository.deleteById(id);
    }
}

package com.ufpb.meuguiaapi.service;

import com.ufpb.meuguiaapi.domain.Attraction;
import com.ufpb.meuguiaapi.domain.MoreInfoLink;
import com.ufpb.meuguiaapi.domain.TurismSegmentation;
import com.ufpb.meuguiaapi.exception.ObjectNotFoundException;
import com.ufpb.meuguiaapi.repository.MoreInfoLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoreInfoLinkService {

    @Autowired
    private MoreInfoLinkRepository moreInfoLinkRepository;

    @Autowired
    private AttractionService attractionService;

    public MoreInfoLink findById(Long id) {
        Optional<MoreInfoLink> obj = moreInfoLinkRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo " + MoreInfoLink.class.getName()));

    }

    public List<MoreInfoLink> findAll() {
        return moreInfoLinkRepository.findAll();
    }

    public MoreInfoLink create(Long id_att, MoreInfoLink obj) {
        obj.setId(null);
        Attraction att = attractionService.findById(id_att);
        obj.setAttraction(att);
        return moreInfoLinkRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        moreInfoLinkRepository.deleteById(id);
    }
}

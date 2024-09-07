package com.ufpb.meuguiaapi.service;

import com.ufpb.meuguiaapi.domain.MoreInfoLink;
import com.ufpb.meuguiaapi.exception.ObjectNotFoundException;
import com.ufpb.meuguiaapi.repository.MoreInfoLinkRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MoreInfoLinkService {

    @Autowired
    private MoreInfoLinkRepository moreInfoLinkRepository;

    public MoreInfoLink findById(Long id) {
        Optional<MoreInfoLink> obj = moreInfoLinkRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo " + MoreInfoLink.class.getName()));

    }

    public List<MoreInfoLink> findAll() {
        return moreInfoLinkRepository.findAll();
    }

    public MoreInfoLink create(MoreInfoLink obj) {
        obj.setId(null);
        return moreInfoLinkRepository.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        moreInfoLinkRepository.deleteById(id);
    }
}

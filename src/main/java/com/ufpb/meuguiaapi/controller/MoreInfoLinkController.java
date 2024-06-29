package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.MoreInfoLink;
import com.ufpb.meuguiaapi.service.MoreInfoLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/more-info")
public class MoreInfoLinkController {

    @Autowired
    private MoreInfoLinkService moreInfoLinkService;

    @GetMapping("/{id}")
    public ResponseEntity<MoreInfoLink> findById(@PathVariable Long id) {
        MoreInfoLink obj = moreInfoLinkService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public List<MoreInfoLink> findAll() {
        List<MoreInfoLink> list = moreInfoLinkService.findAll();
        return ResponseEntity.ok().body(list).getBody();
    }

    @PostMapping
    public ResponseEntity<MoreInfoLink> create(@RequestBody MoreInfoLink obj) {
        MoreInfoLink newObj = moreInfoLinkService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/more-info/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        moreInfoLinkService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

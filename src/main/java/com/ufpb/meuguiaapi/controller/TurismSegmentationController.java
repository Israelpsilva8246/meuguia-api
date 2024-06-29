package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.TurismSegmentation;
import com.ufpb.meuguiaapi.service.TurismSegmentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/segmentations")
public class TurismSegmentationController {

    @Autowired
    private TurismSegmentationService turismSegmentationService;

    @GetMapping("/{id}")
    public ResponseEntity<TurismSegmentation> findById(@PathVariable Long id) {
        TurismSegmentation obj = turismSegmentationService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public List<TurismSegmentation> findAll() {
        List<TurismSegmentation> list = turismSegmentationService.findAll();
        return ResponseEntity.ok().body(list).getBody();
    }

    @PostMapping
    public ResponseEntity<TurismSegmentation> create(@RequestBody TurismSegmentation obj) {
        TurismSegmentation newObj = turismSegmentationService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/segmentations/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        turismSegmentationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

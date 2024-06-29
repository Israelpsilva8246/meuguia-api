package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.Attraction;
import com.ufpb.meuguiaapi.dtos.TuristAttractionDTO;
import com.ufpb.meuguiaapi.service.AttractionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/turists")
@AllArgsConstructor
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Attraction> findById(@PathVariable Long id) {
        Attraction obj = attractionService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/create")
    public ResponseEntity<Attraction> create(@RequestBody Attraction obj) {
        obj = attractionService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attractionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TuristAttractionDTO>> findAll() {
        List<Attraction> list = attractionService.findAll();
        List<TuristAttractionDTO> listDTO = list.stream().map(TuristAttractionDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/byName")
    public ResponseEntity<List<Attraction>> findByName(@RequestParam String name) {
        List<Attraction> list = attractionService.findByName(name);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/byCity")
    public ResponseEntity<List<Attraction>> findByCity(@RequestParam String city) {
        List<Attraction> list = attractionService.findByCity(city);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/bySegmentations")
    public ResponseEntity<List<Attraction>> findBySegmentations(@RequestParam String segmentations) {
        List<Attraction> list = attractionService.findBySegmentations(segmentations);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/byType")
    public ResponseEntity<List<Attraction>> findByType(@RequestParam String attractionTypes) {
        List<Attraction> list = attractionService.findByType(attractionTypes);
        return ResponseEntity.ok().body(list);
    }
}

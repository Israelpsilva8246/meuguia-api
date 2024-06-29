package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.AttractionType;
import com.ufpb.meuguiaapi.service.AttractionTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/types")
@AllArgsConstructor
public class AttractionTypeController {


    private AttractionTypeService attractionTypeService;

    @PostMapping
    public ResponseEntity<AttractionType> create(@RequestBody AttractionType obj) {
        AttractionType newObj = attractionTypeService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/types/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attractionTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AttractionType>> findAll() {
        List<AttractionType> list = attractionTypeService.findAll();
        return ResponseEntity.ok().body(list);
    }

}

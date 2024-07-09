package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.Attraction;
import com.ufpb.meuguiaapi.dtos.TuristAttractionDTO;
import com.ufpb.meuguiaapi.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/turists", produces = {"application/json"})
@AllArgsConstructor
@Tag(name = "Attractions", description = "Endpoints para gerenciar atrativos")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @Operation(summary = "Buscar atrativo por id", description = "Busca um atrativo passando o seu id",
            tags = {"Attractions"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TuristAttractionDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<Attraction> findById(@PathVariable Long id) {
        Attraction obj = attractionService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(summary = "Cadastro de atrativo",
            description = "Cadastra um atrativo",
            tags = {"Attractions"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = TuristAttractionDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/create")
    public ResponseEntity<Attraction> create(@RequestBody Attraction obj) {
        obj = attractionService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um atrativo",
            description = "Deleta um Atrativo passando o seu id",
            tags = {"Attractions"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attractionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listagem de todos os atrativos", description = "Lista todos os atrativos",
            tags = {"Attractions"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = TuristAttractionDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping
    public ResponseEntity<List<TuristAttractionDTO>> findAll() {
        List<Attraction> list = attractionService.findAll();
        List<TuristAttractionDTO> listDTO = list.stream().map(TuristAttractionDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @Operation(summary = "Buscar atrativo por nome", description = "Busca um atrativo pelo nome",
            tags = {"Attractions"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TuristAttractionDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/byName")
    public ResponseEntity<List<Attraction>> findByName(@RequestParam String name) {
        List<Attraction> list = attractionService.findByName(name);
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar atrativo por cidade", description = "Busca um atrativo por cidade",
            tags = {"Attractions"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TuristAttractionDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/byCity")
    public ResponseEntity<List<Attraction>> findByCity(@RequestParam String city) {
        List<Attraction> list = attractionService.findByCity(city);
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar atrativo por segmentação", description = "Busca um atrativo pela segmentação",
            tags = {"Attractions"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TuristAttractionDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/bySegmentations")
    public ResponseEntity<List<Attraction>> findBySegmentations(@RequestParam String segmentations) {
        List<Attraction> list = attractionService.findBySegmentations(segmentations);
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Buscar atrativo por tipo", description = "Busca um atrativo pelo tipo",
            tags = {"Attractions"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TuristAttractionDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/byType")
    public ResponseEntity<List<Attraction>> findByType(@RequestParam String attractionTypes) {
        List<Attraction> list = attractionService.findByType(attractionTypes);
        return ResponseEntity.ok().body(list);
    }
}

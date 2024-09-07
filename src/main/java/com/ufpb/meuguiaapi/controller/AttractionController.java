package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.Attraction;
import com.ufpb.meuguiaapi.domain.MoreInfoLink;
import com.ufpb.meuguiaapi.dtos.TuristAttractionDTO;
import com.ufpb.meuguiaapi.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);


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
        logger.info("Buscando atrativo pelo ID: {}", id);
        Attraction obj = attractionService.findById(id);
        logger.info("Atrativo encontrado pelo ID: {}", obj);

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
        logger.info("Criando novo atrativo: {}", obj);
        Attraction newObj = attractionService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/turists/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        logger.info("Atrativo criado com sucesso: {}", newObj);
        return ResponseEntity.created(uri).body(obj);
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
        logger.info("Deletando atrativo com ID: {}", id);
        attractionService.delete(id);
        logger.info("Atrativo deletado com sucesso");
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
        logger.info("Buscando todos os atrativos");
        List<Attraction> list = attractionService.findAll();
        List<TuristAttractionDTO> listDTO = list.stream().map(TuristAttractionDTO::new).collect(Collectors.toList());
        logger.info("Atrativos encontrados: {}", listDTO.size());
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
        logger.info("Buscando atrativo pelo nome: {}", name);
        List<Attraction> list = attractionService.findByName(name);
        logger.info("Atrativo encontrado pelo nome: {}", list.size());
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
        logger.info("Buscando atrativo pela cidade: {}", city);
        List<Attraction> list = attractionService.findByCity(city);
        logger.info("Atrativos encontrados pela cidade: {}", list.size());
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
        logger.info("Buscando atrativo pela segmentação: {}", segmentations);
        List<Attraction> list = attractionService.findBySegmentations(segmentations);
        logger.info("Atrativos encontrados pela segmentação: {}", list.size());
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
        logger.info("Buscando atrativo pelo tipo: {}", attractionTypes);
        List<Attraction> list = attractionService.findByType(attractionTypes);
        logger.info("Atrativos encontrados pelo tipo: {}", list.size());
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TuristAttractionDTO> update(@PathVariable Long id, @RequestBody TuristAttractionDTO objDto) {
        logger.info("Atualizando atrativo com ID: {}", id);
        Attraction newObj = attractionService.update(id, objDto);
        logger.info("Atrativo atualizado com sucesso: {}", newObj);
        return ResponseEntity.ok().body(new TuristAttractionDTO(newObj));
    }
}

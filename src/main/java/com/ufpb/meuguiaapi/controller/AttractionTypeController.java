package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.AttractionType;
import com.ufpb.meuguiaapi.dtos.TuristAttractionDTO;
import com.ufpb.meuguiaapi.service.AttractionTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/types", produces = {"application/json"})
@AllArgsConstructor
@Tag(name = "Attractions Types", description = "Endpoints para gerenciar tipos de atrativos")
public class AttractionTypeController {

    private static final Logger logger = LoggerFactory.getLogger(AttractionTypeController.class);

    private AttractionTypeService attractionTypeService;

    @Operation(summary = "Cadastro de tipos de atrativos",
            description = "Cadastra um tipo de atrativo",
            tags = {"Attractions Types"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = TuristAttractionDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping
    public ResponseEntity<AttractionType> create(@RequestBody AttractionType obj) {
        logger.info("Criando novo tipo de atrativo: {}", obj);
        AttractionType newObj = attractionTypeService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/types/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        logger.info("Tipo de atrativo criado com sucesso: {}", newObj);
        return ResponseEntity.created(uri).body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta um tipo de atrativo",
            description = "Deleta um tipo de Atrativo passando o seu id",
            tags = {"Attractions Types"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deletando tipo de atrativo com ID: {}", id);
        attractionTypeService.delete(id);
        logger.info("Tipo de atrativo deletado com sucesso");
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listagem de todos os tipos de atrativos", description = "Lista todos os tipos de atrativos",
            tags = {"Attractions Types"},
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
    public ResponseEntity<List<AttractionType>> findAll() {
        logger.info("Buscando todos os tipos de atrativos");
        List<AttractionType> list = attractionTypeService.findAll();
        logger.info("Tipos de atrativos encontrados: {}", list.size());
        return ResponseEntity.ok().body(list);
    }
}

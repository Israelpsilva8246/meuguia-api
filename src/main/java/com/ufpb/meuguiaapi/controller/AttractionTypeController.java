package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.AttractionType;
import com.ufpb.meuguiaapi.domain.MoreInfoLink;
import com.ufpb.meuguiaapi.dtos.TuristAttractionDTO;
import com.ufpb.meuguiaapi.service.AttractionTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
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


    private AttractionTypeService attractionTypeService;

    @Operation(summary = "Cadastro de tipos de atrativos",
            description = "Cadastra um um tipo de atrativo",
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
    public ResponseEntity<AttractionType> create(@RequestParam(value = "attraction", defaultValue = "0") Long id_att,
                                                 @RequestBody AttractionType obj) {
        AttractionType newObj = attractionTypeService.create(id_att, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/types/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
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
        attractionTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listagem de todos os tipos de atrativos", description = "Lista todos os tipos de atrativos atrativos",
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
    public ResponseEntity<List<AttractionType>> findAll() {
        List<AttractionType> list = attractionTypeService.findAll();
        return ResponseEntity.ok().body(list);
    }

}

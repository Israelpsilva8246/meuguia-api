package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.TurismSegmentation;
import com.ufpb.meuguiaapi.dtos.TuristAttractionDTO;
import com.ufpb.meuguiaapi.service.TurismSegmentationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/segmentations", produces = {"application/json"})
@Tag(name = "Turism Segmentation", description = "Endpoints para gerenciar as segmentações turisticas dos atrativos")
@Tag(name = "Turism Segmentation")
public class TurismSegmentationController {

    @Autowired
    private TurismSegmentationService turismSegmentationService;

    @Operation(summary = "Buscar a segmentação turistica do atrativo por id", description = "Busca uma segmentação turistica do atrativo por id",
            tags = {"Turism Segmentation"},
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
    public ResponseEntity<TurismSegmentation> findById(@PathVariable Long id) {
        TurismSegmentation obj = turismSegmentationService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(summary = "Listagem de todas as segmentações turisticas do atrativo", description = "Lista todas as segmentações turisticas do atrativo",
            tags = {"Turism Segmentation"},
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
    public List<TurismSegmentation> findAll() {
        List<TurismSegmentation> list = turismSegmentationService.findAll();
        return ResponseEntity.ok().body(list).getBody();
    }

    @Operation(summary = "Cadastro de segmentações turisticas",
            description = "Cadastra as segmentações turisticas dos atrativos",
            tags = {"Turism Segmentation"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = TuristAttractionDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping
    public ResponseEntity<TurismSegmentation> create(@RequestBody TurismSegmentation obj) {
        TurismSegmentation newObj = turismSegmentationService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/segmentations/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta uma segmentação turistica passando o seu id",
            description = "Deleta uma segmentação turistica passando o seu id",
            tags = {"Turism Segmentation"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        turismSegmentationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

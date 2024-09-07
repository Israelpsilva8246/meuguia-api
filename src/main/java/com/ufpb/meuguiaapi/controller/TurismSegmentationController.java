package com.ufpb.meuguiaapi.controller;

import com.ufpb.meuguiaapi.domain.TouristSegmentation;
import com.ufpb.meuguiaapi.dtos.TuristAttractionDTO;
import com.ufpb.meuguiaapi.service.TurismSegmentationService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/segmentations", produces = {"application/json"})
@Tag(name = "Turism Segmentation", description = "Endpoints para gerenciar as segmentações turísticas dos atrativos")
@AllArgsConstructor
public class TurismSegmentationController {

    private static final Logger logger = LoggerFactory.getLogger(TurismSegmentationController.class);

    @Autowired
    private TurismSegmentationService turismSegmentationService;

    @Operation(summary = "Buscar a segmentação turística do atrativo por id", description = "Busca uma segmentação turística do atrativo por id",
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
    public ResponseEntity<TouristSegmentation> findById(@PathVariable Long id) {
        logger.info("Buscando segmentação turística com ID: {}", id);
        TouristSegmentation obj = turismSegmentationService.findById(id);
        logger.info("Segmentação turística encontrada: {}", obj);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(summary = "Listagem de todas as segmentações turísticas do atrativo",
            description = "Lista todas as segmentações turísticas do atrativo",
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
    public ResponseEntity<List<TouristSegmentation>> findAll() {
        logger.info("Buscando todas as segmentações turísticas");
        List<TouristSegmentation> list = turismSegmentationService.findAll();
        logger.info("Total de segmentações encontradas: {}", list.size());
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Cadastro de segmentações turísticas",
            description = "Cadastra as segmentações turísticas dos atrativos",
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
    public ResponseEntity<TouristSegmentation> create(@RequestBody TouristSegmentation obj) {
        logger.info("Cadastrando nova segmentação turística: {}", obj);
        TouristSegmentation newObj = turismSegmentationService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/segmentations/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        logger.info("Segmentação turística criada com sucesso: {}", newObj);
        return ResponseEntity.created(uri).body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta uma segmentação turística passando o seu id",
            description = "Deleta uma segmentação turística passando o seu id",
            tags = {"Turism Segmentation"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deletando segmentação turística com ID: {}", id);
        turismSegmentationService.delete(id);
        logger.info("Segmentação turística deletada com sucesso");
        return ResponseEntity.noContent().build();
    }
}

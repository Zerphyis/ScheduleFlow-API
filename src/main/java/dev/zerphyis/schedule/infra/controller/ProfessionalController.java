package dev.zerphyis.schedule.infra.controller;

import dev.zerphyis.schedule.application.useCases.Professional.ProfessionalService;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professionals", description = "Gerenciamento de profissionais")
@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    private final ProfessionalService facade;

    public ProfessionalController(ProfessionalService facade) {
        this.facade = facade;
    }

    @Operation(summary = "Criar profissional")
    @PostMapping
    public ResponseEntity<ProfessionalResponseDTO> create(
            @RequestBody @Valid ProfessionalRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(facade.create(dto));
    }

    @Operation(summary = "Buscar profissional por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(facade.findById(id));
    }

    @Operation(summary = "Listar todos os profissionais")
    @GetMapping
    public ResponseEntity<List<ProfessionalResponseDTO>> findAll() {
        return ResponseEntity.ok(facade.findAll());
    }

    @Operation(summary = "Atualizar profissional")
    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ProfessionalRequestDTO dto
    ) {
        return ResponseEntity.ok(facade.update(id, dto));
    }

    @Operation(summary = "Deletar profissional")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity.noContent().build();
    }
}
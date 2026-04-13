package dev.zerphyis.schedule.infra.controller;

import dev.zerphyis.schedule.application.useCases.Professional.ProfessionalService;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {
    private final ProfessionalService facade;

    public ProfessionalController(ProfessionalService facade) {
        this.facade = facade;
    }


    @PostMapping
    public ResponseEntity<ProfessionalResponseDTO> create(
            @RequestBody @Valid ProfessionalRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(facade.create(dto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(facade.findById(id));
    }


    @GetMapping
    public ResponseEntity<List<ProfessionalResponseDTO>> findAll() {
        return ResponseEntity.ok(facade.findAll());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ProfessionalRequestDTO dto
    ) {
        return ResponseEntity.ok(facade.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity.noContent().build();
    }
}

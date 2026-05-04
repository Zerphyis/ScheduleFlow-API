package dev.zerphyis.schedule.infra.controller;


import dev.zerphyis.schedule.application.useCases.Client.ClientService;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientResponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Clients", description = "Gerenciamento de clientes")
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @Operation(summary = "Criar cliente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDTO create(@RequestBody @Valid ClientRequestDTO dto) {
        return service.create(dto);
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ClientResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public List<ClientResponseDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Atualizar cliente")
    @PutMapping("/{id}")
    public ClientResponseDTO update(
            @PathVariable Long id,
            @RequestBody @Valid ClientRequestDTO dto
    ) {
        return service.update(id, dto);
    }

    @Operation(summary = "Deletar cliente")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
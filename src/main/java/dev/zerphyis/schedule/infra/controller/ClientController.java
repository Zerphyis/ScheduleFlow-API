package dev.zerphyis.schedule.infra.controller;


import dev.zerphyis.schedule.application.useCases.Client.ClientService;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientResponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDTO create(@RequestBody @Valid ClientRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public ClientResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<ClientResponseDTO> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ClientResponseDTO update(
            @PathVariable Long id,
            @RequestBody @Valid ClientRequestDTO dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

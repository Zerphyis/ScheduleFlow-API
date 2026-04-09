package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientReponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;

import java.util.List;

public class ClientService {
    private final CreateClientUseCase createUseCase;
    private final FindByIdClientUseCase findByIdUseCase;
    private final FindAllClientUseCase findAllUseCase;
    private final UpdateClientUseCase updateUseCase;
    private final DeleteClientUseCase deleteUseCase;

    public ClientService(
            CreateClientUseCase createUseCase,
            FindByIdClientUseCase findByIdUseCase,
            FindAllClientUseCase findAllUseCase,
            UpdateClientUseCase updateUseCase,
            DeleteClientUseCase deleteUseCase
    ) {
        this.createUseCase = createUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findAllUseCase = findAllUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    public ClientReponseDTO create(ClientRequestDTO dto) {
        return createUseCase.execute(dto);
    }

    public ClientReponseDTO findById(Long id) {
        return findByIdUseCase.execute(id);
    }

    public List<ClientReponseDTO> findAll() {
        return findAllUseCase.execute();
    }

    public ClientReponseDTO update(Long id, ClientRequestDTO dto) {
        return updateUseCase.execute(id, dto);
    }

    public void delete(Long id) {
        deleteUseCase.execute(id);
    }
}

package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.application.exception.clientException.ClientNotFoundException;
import dev.zerphyis.schedule.application.interfaceCases.Client.FindByidClientCaseInterface;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.ClientReponseDTO;

public class FindByIdClientUseCase implements FindByidClientCaseInterface {

    private final ClientRepository repository;

    public FindByIdClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientReponseDTO execute(Long id) {

        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        return new ClientReponseDTO(
                client.getId(),
                client.getNome(),
                client.getCpf(),
                client.getTelefone()
        );
    }

}

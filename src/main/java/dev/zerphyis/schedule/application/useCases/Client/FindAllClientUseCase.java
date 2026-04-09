package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.application.interfaceCases.Client.FindAllClientsCaseInterface;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientReponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllClientUseCase  implements FindAllClientsCaseInterface {
    private final ClientRepository repository;

    public FindAllClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientReponseDTO> execute() {

        return repository.findAll()
                .stream()
                .map(client -> new ClientReponseDTO(
                        client.getId(),
                        client.getNome(),
                        client.getCpf(),
                        client.getTelefone()
                ))
                .collect(Collectors.toList());
    }
}

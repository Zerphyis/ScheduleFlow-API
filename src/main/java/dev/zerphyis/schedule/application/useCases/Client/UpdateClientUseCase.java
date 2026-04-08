package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.application.exception.clientException.ClientAlreadyExistsException;
import dev.zerphyis.schedule.application.exception.clientException.ClientNotFoundException;
import dev.zerphyis.schedule.application.interfaceCases.Client.UpdateClientCaseInterface;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.ClientReponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.ClientRequestDTO;

public class UpdateClientUseCase implements UpdateClientCaseInterface {
    private final ClientRepository repository;

    public UpdateClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientReponseDTO execute(Long id, ClientRequestDTO dto) {

        Client existing = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        if (!existing.getCpf().equals(dto.cpf())
                && repository.existsByCpf(dto.cpf())) {
            throw new ClientAlreadyExistsException(dto.cpf());
        }

        existing.setNome(dto.nome());
        existing.setCpf(dto.cpf());
        existing.setTelefone(dto.telefone());

        Client updated = repository.save(existing);

        return new ClientReponseDTO(
                updated.getId(),
                updated.getNome(),
                updated.getCpf(),
                updated.getTelefone()
        );
    }
}


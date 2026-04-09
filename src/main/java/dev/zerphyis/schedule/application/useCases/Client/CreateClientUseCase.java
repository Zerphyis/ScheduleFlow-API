package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.application.exception.clientException.ClientAlreadyExistsException;
import dev.zerphyis.schedule.application.exception.clientException.InvalidClientDataException;
import dev.zerphyis.schedule.application.interfaceCases.Client.CreateClientCaseInterface;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientReponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;

public class CreateClientUseCase implements CreateClientCaseInterface {
    private final ClientRepository repository;

    public CreateClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientReponseDTO execute(ClientRequestDTO dto) {

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new InvalidClientDataException("Nome é obrigatório");
        }

        if (dto.cpf() == null || dto.cpf().isBlank()) {
            throw new InvalidClientDataException("CPF é obrigatório");
        }

        if (repository.existsByCpf(dto.cpf())) {
            throw new ClientAlreadyExistsException(dto.cpf());
        }

        Client client = new Client(
                null,
                dto.nome(),
                dto.cpf(),
                dto.telefone()
        );

        Client saved = repository.save(client);

        return new ClientReponseDTO(
                saved.getId(),
                saved.getNome(),
                saved.getCpf(),
                saved.getTelefone()
        );
    }
}

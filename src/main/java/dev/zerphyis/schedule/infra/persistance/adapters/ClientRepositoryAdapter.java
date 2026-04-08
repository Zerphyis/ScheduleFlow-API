package dev.zerphyis.schedule.infra.persistance.adapters;

import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.infra.persistance.entites.ClientJpa;
import dev.zerphyis.schedule.infra.persistance.repository.ClientRepositoryJpa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component

public class ClientRepositoryAdapter implements ClientRepository {

    private final ClientRepositoryJpa repository;

    public ClientRepositoryAdapter(ClientRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Client save(Client client) {
        ClientJpa entity = toJpa(client);
        return toDomain(repository.save(entity));
    }

    @Override
    public Optional<Client> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private ClientJpa toJpa(Client client) {
        return new ClientJpa(
                client.getId(),
                client.getNome(),
                client.getCpf(),
                client.getTelefone()
        );
    }

    private Client toDomain(ClientJpa jpa) {
        return new Client(
                jpa.getId(),
                jpa.getNome(),
                jpa.getCpf(),
                jpa.getTelefone()
        );
    }
}

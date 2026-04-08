package dev.zerphyis.schedule.domain.repositories;

import dev.zerphyis.schedule.domain.entites.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {


    Client save(Client client);

    Optional<Client> findById(Long id);

    List<Client> findAll();

    void deleteById(Long id);

    boolean existsByCpf(String cpf);
    }


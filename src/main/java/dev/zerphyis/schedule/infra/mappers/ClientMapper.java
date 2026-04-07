package dev.zerphyis.schedule.infra.mappers;

import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.infra.persistance.entites.ClientJpa;

public class ClientMapper {

    public static Client toDomain(ClientJpa entity) {
        if (entity == null) return null;

        Client client = new Client();
        client.setId(entity.getId());
        client.setNome(entity.getNome());
        client.setCpf(entity.getCpf());
        client.setTelefone(entity.getTelefone());

        return client;
    }

    public static ClientJpa toEntity(Client domain) {
        if (domain == null) return null;

        ClientJpa entity = new ClientJpa();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setCpf(domain.getCpf());
        entity.setTelefone(domain.getTelefone());

        return entity;
    }
}
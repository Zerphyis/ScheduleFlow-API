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

            return new ClientJpa(
                    domain.getId(),
                    domain.getNome(),
                    domain.getCpf(),
                    domain.getTelefone()
            );
        }
    }

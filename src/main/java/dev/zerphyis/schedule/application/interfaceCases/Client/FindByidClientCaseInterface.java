package dev.zerphyis.schedule.application.interfaceCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientResponseDTO;

public interface FindByidClientCaseInterface {
    ClientResponseDTO execute(Long id);
}

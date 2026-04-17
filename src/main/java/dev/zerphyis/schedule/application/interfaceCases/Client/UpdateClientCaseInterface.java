package dev.zerphyis.schedule.application.interfaceCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientResponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;

public interface UpdateClientCaseInterface {
    ClientResponseDTO execute(Long id, ClientRequestDTO dto);
}

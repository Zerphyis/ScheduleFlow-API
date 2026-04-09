package dev.zerphyis.schedule.application.interfaceCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientReponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;

public interface UpdateClientCaseInterface {
    ClientReponseDTO execute(Long id, ClientRequestDTO dto);
}

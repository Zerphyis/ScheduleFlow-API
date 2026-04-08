package dev.zerphyis.schedule.application.interfaceCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.ClientReponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.ClientRequestDTO;

public interface CreateClientCaseInterface {
    ClientReponseDTO execute(ClientRequestDTO dto);
}

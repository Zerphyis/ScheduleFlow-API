package dev.zerphyis.schedule.application.interfaceCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientResponseDTO;

import java.util.List;

public interface FindAllClientsCaseInterface {
    List<ClientResponseDTO> execute();
}

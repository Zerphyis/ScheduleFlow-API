package dev.zerphyis.schedule.application.interfaceCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientReponseDTO;

import java.util.List;

public interface FindAllClientsCaseInterface {
    List<ClientReponseDTO> execute();
}

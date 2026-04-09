package dev.zerphyis.schedule.application.interfaceCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientReponseDTO;

public interface FindByidClientCaseInterface {
    ClientReponseDTO execute(Long id);
}

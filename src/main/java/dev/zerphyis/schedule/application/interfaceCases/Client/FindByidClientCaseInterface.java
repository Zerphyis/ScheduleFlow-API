package dev.zerphyis.schedule.application.interfaceCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.ClientReponseDTO;

public interface FindByidClientCaseInterface {
    ClientReponseDTO execute(Long id);
}

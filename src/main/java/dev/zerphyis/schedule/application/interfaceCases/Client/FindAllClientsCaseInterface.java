package dev.zerphyis.schedule.application.interfaceCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.ClientReponseDTO;

import java.util.List;

public interface FindAllClientsCaseInterface {
    List<ClientReponseDTO> execute();
}

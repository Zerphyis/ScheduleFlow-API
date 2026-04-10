package dev.zerphyis.schedule.application.interfaceCases.Professional;

import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;

import java.util.List;

public interface FindAllProfessionalCaseInterface {
    List<ProfessionalResponseDTO> execute();
}

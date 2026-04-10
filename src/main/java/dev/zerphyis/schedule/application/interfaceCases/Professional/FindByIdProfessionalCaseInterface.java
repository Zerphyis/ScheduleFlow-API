package dev.zerphyis.schedule.application.interfaceCases.Professional;

import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;

public interface FindByIdProfessionalCaseInterface {
    ProfessionalResponseDTO execute(Long id);
}

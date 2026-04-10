package dev.zerphyis.schedule.application.interfaceCases.Professional;

import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;

public interface UpdateProfessionalCaseInterface {
    ProfessionalResponseDTO execute(Long id, ProfessionalRequestDTO dto);
}

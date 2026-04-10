package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.application.exception.ProfessionalException.ProfessionalNotFoundException;
import dev.zerphyis.schedule.application.interfaceCases.Professional.FindByIdProfessionalCaseInterface;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;

public class FindProfessionalByIdUseCase implements FindByIdProfessionalCaseInterface {
    private final ProfessionalRepository repository;

    public FindProfessionalByIdUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfessionalResponseDTO execute(Long id) {
        var professional = repository.findById(id)
                .orElseThrow(() -> new ProfessionalNotFoundException(id));

        return new ProfessionalResponseDTO(
                professional.getId(),
                professional.getNome(),
                professional.getEspecialidade(),
                professional.getEmail()
        );
    }
}

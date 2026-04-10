package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.application.interfaceCases.Professional.FindAllProfessionalCaseInterface;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;

import java.util.List;

public class FindAllProfessionalUseCase implements FindAllProfessionalCaseInterface {
    private final ProfessionalRepository repository;

    public FindAllProfessionalUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProfessionalResponseDTO> execute() {
        return repository.findAll()
                .stream()
                .map(p -> new ProfessionalResponseDTO(
                        p.getId(),
                        p.getNome(),
                        p.getEspecialidade(),
                        p.getEmail()
                ))
                .toList();
    }
}

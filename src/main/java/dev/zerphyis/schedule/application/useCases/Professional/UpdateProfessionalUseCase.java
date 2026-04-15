package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.application.exception.ProfessionalException.InvalidProfessionalDataException;
import dev.zerphyis.schedule.application.exception.ProfessionalException.ProfessionalNotFoundException;
import dev.zerphyis.schedule.application.interfaceCases.Professional.UpdateProfessionalCaseInterface;
import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;

public class UpdateProfessionalUseCase  implements UpdateProfessionalCaseInterface {

    private final ProfessionalRepository repository;

    public UpdateProfessionalUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfessionalResponseDTO execute(Long id, ProfessionalRequestDTO dto) {

        Professional existing = repository.findById(id)
                .orElseThrow(() -> new ProfessionalNotFoundException(id));

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new InvalidProfessionalDataException("Nome é obrigatório");
        }

        if (dto.especialidade() == null || dto.especialidade().isBlank()) {
            throw new InvalidProfessionalDataException("Especialidade é obrigatória");
        }

        if (dto.email() == null || dto.email().isBlank()) {
            throw new InvalidProfessionalDataException("Email é obrigatório");
        }

        existing.setNome(dto.nome());
        existing.setEspecialidade(dto.especialidade());
        existing.setEmail(dto.email());

        Professional updated = repository.save(existing);

        return new ProfessionalResponseDTO(
                updated.getId(),
                updated.getNome(),
                updated.getEspecialidade(),
                updated.getEmail()
        );
    }
}

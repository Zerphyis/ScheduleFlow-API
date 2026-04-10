package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.application.interfaceCases.Professional.CreateProfissionalCaseInterface;
import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;

public class CreateProfessionalUseCase implements CreateProfissionalCaseInterface {
    private final ProfessionalRepository repository;

    public CreateProfessionalUseCase(ProfessionalRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfessionalResponseDTO execute(ProfessionalRequestDTO dto) {

        Professional professional = new Professional();
        professional.setNome(dto.nome());
        professional.setEspecialidade(dto.especialidade());
        professional.setEmail(dto.email());

        Professional saved = repository.save(professional);

        return new ProfessionalResponseDTO(
                saved.getId(),
                saved.getNome(),
                saved.getEspecialidade(),
                saved.getEmail()
        );
    }
}

package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;

import java.util.List;

public class ProfessionalService {
    private final CreateProfessionalUseCase createUseCase;
    private final FindProfessionalByIdUseCase findByIdUseCase;
    private final FindAllProfessionalUseCase findAllUseCase;
    private final UpdateProfessionalUseCase updateUseCase;
    private final DeleteProfessionalUseCase deleteUseCase;

    public ProfessionalService(
            CreateProfessionalUseCase createUseCase,
            FindProfessionalByIdUseCase findByIdUseCase,
            FindAllProfessionalUseCase findAllUseCase,
            UpdateProfessionalUseCase updateUseCase,
            DeleteProfessionalUseCase deleteUseCase
    ) {
        this.createUseCase = createUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findAllUseCase = findAllUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    public ProfessionalResponseDTO create(ProfessionalRequestDTO dto) {
        return createUseCase.execute(dto);
    }

    public ProfessionalResponseDTO findById(Long id) {
        return findByIdUseCase.execute(id);
    }

    public List<ProfessionalResponseDTO> findAll() {
        return findAllUseCase.execute();
    }

    public ProfessionalResponseDTO update(Long id, ProfessionalRequestDTO dto) {
        return updateUseCase.execute(id, dto);
    }

    public void delete(Long id) {
        deleteUseCase.execute(id);
    }
}

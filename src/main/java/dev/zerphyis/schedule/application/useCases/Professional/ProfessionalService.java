package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

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

    @CacheEvict(value = {"professionals", "professionalsList"}, allEntries = true)
    public ProfessionalResponseDTO create(ProfessionalRequestDTO dto) {
        return createUseCase.execute(dto);
    }

    @Cacheable(value = "professionals", key = "#id")
    public ProfessionalResponseDTO findById(Long id) {
        return findByIdUseCase.execute(id);
    }

    @Cacheable(value = "professionalsList")
    public List<ProfessionalResponseDTO> findAll() {
        return findAllUseCase.execute();
    }

    @CacheEvict(value = {"professionals", "professionalsList"}, allEntries = true)
    public ProfessionalResponseDTO update(Long id, ProfessionalRequestDTO dto) {
        return updateUseCase.execute(id, dto);
    }

    @CacheEvict(value = {"professionals", "professionalsList"}, allEntries = true)
    public void delete(Long id) {
        deleteUseCase.execute(id);
    }
}
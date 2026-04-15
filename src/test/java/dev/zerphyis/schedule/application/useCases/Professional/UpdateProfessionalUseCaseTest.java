package dev.zerphyis.schedule.application.useCases.Professional;


import dev.zerphyis.schedule.application.exception.ProfessionalException.InvalidProfessionalDataException;
import dev.zerphyis.schedule.application.exception.ProfessionalException.ProfessionalNotFoundException;
import dev.zerphyis.schedule.application.useCases.Professional.UpdateProfessionalUseCase;
import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateProfessionalUseCaseTest {

    private ProfessionalRepository repository;
    private UpdateProfessionalUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ProfessionalRepository.class);
        useCase = new UpdateProfessionalUseCase(repository);
    }

    @Test
    void shouldUpdateSuccessfully() {
        var existing = new Professional(1L, "Old", "OldSpec", "old@email");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        var dto = new ProfessionalRequestDTO("New", "NewSpec", "new@email");

        var result = useCase.execute(1L, dto);

        assertAll(
                () -> assertEquals("New", result.nome()),
                () -> assertEquals("NewSpec", result.especialidade()),
                () -> assertEquals("new@email", result.email())
        );

        verify(repository).save(existing);
    }

    @Test
    void shouldThrowWhenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        var dto = new ProfessionalRequestDTO("New", "Spec", "email");

        assertThrows(ProfessionalNotFoundException.class,
                () -> useCase.execute(1L, dto));

        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowWhenNameInvalid() {
        var existing = new Professional(1L, "Old", "Spec", "email");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));

        var dto = new ProfessionalRequestDTO("", "Spec", "email");

        assertThrows(InvalidProfessionalDataException.class,
                () -> useCase.execute(1L, dto));

        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowWhenEspecialidadeInvalid() {
        var existing = new Professional(1L, "Old", "Spec", "email");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));

        var dto = new ProfessionalRequestDTO("Nome", "", "email");

        assertThrows(InvalidProfessionalDataException.class,
                () -> useCase.execute(1L, dto));

        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowWhenEmailInvalid() {
        var existing = new Professional(1L, "Old", "Spec", "email");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));

        var dto = new ProfessionalRequestDTO("Nome", "Spec", "");

        assertThrows(InvalidProfessionalDataException.class,
                () -> useCase.execute(1L, dto));

        verify(repository, never()).save(any());
    }
}
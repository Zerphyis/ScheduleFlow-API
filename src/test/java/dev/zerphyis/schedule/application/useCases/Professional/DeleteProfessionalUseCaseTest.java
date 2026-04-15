package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.application.exception.ProfessionalException.ProfessionalNotFoundException;
import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteProfessionalUseCaseTest {
    private ProfessionalRepository repository;
    private DeleteProfessionalUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ProfessionalRepository.class);
        useCase = new DeleteProfessionalUseCase(repository);
    }

    @Test
    void shouldDeleteSuccessfully() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(new Professional()));

        useCase.execute(1L);

        verify(repository).findById(1L);
        verify(repository).deleteById(1L);
    }
    @Test
    void shouldThrowWhenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProfessionalNotFoundException.class,
                () -> useCase.execute(1L));

        verify(repository, never()).deleteById(any());
    }

}
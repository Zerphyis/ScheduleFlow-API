package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.application.exception.ProfessionalException.ProfessionalNotFoundException;
import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindProfessionalByIdUseCaseTest {
    private ProfessionalRepository repository;
    private FindProfessionalByIdUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ProfessionalRepository.class);
        useCase = new FindProfessionalByIdUseCase(repository);
    }

    @Test
    void shouldReturnProfessional() {
        var p = new Professional(1L, "Otavio", "Dev", "email");

        when(repository.findById(1L)).thenReturn(Optional.of(p));

        var result = useCase.execute(1L);

        assertEquals("Otavio", result.nome());
    }

    @Test
    void shouldThrowWhenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProfessionalNotFoundException.class,
                () -> useCase.execute(1L));
    }

}
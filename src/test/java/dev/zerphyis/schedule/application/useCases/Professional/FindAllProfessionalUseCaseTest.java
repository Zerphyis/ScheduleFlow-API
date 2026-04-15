package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindAllProfessionalUseCaseTest {


    private ProfessionalRepository repository;
    private FindAllProfessionalUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ProfessionalRepository.class);
        useCase = new FindAllProfessionalUseCase(repository);
    }

    @Test
    void shouldReturnList() {
        var p1 = new Professional(1L, "A", "Dev", "a@email");
        var p2 = new Professional(2L, "B", "QA", "b@email");

        when(repository.findAll()).thenReturn(List.of(p1, p2));

        var result = useCase.execute();

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptyList() {
        when(repository.findAll()).thenReturn(List.of());

        var result = useCase.execute();

        assertTrue(result.isEmpty());
    }
}
package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindAllClientUseCaseTest {

    private ClientRepository repository;
    private FindAllClientUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ClientRepository.class);
        useCase = new FindAllClientUseCase(repository);
    }

    @Test
    void shouldReturnListWithMultipleClients() {
        var c1 = new Client(1L, "A", "111", "999");
        var c2 = new Client(2L, "B", "222", "888");

        when(repository.findAll()).thenReturn(List.of(c1, c2));

        var result = useCase.execute();

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("A", result.get(0).nome()),
                () -> assertEquals("B", result.get(1).nome())
        );
    }

    @Test
    void shouldReturnEmptyList() {
        when(repository.findAll()).thenReturn(List.of());

        var result = useCase.execute();

        assertTrue(result.isEmpty());
    }

}
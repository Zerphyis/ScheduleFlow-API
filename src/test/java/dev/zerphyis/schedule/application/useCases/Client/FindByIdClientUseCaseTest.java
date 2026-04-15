package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.application.exception.clientException.ClientNotFoundException;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdClientUseCaseTest {

    private ClientRepository repository;
    private FindByIdClientUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ClientRepository.class);
        useCase = new FindByIdClientUseCase(repository);
    }

    @Test
    void shouldReturnClient() {
        var client = new Client(1L, "Otavio", "123", "999");

        when(repository.findById(1L)).thenReturn(Optional.of(client));

        var result = useCase.execute(1L);

        assertAll(
                () -> assertEquals("Otavio", result.nome()),
                () -> assertEquals("123", result.cpf())
        );
    }

    @Test
    void shouldThrowWhenClientNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class,
                () -> useCase.execute(1L));
    }

}
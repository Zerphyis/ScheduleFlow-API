package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.application.exception.clientException.ClientAlreadyExistsException;
import dev.zerphyis.schedule.application.exception.clientException.ClientNotFoundException;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UpdateClientUseCaseTest {
    private ClientRepository repository;
    private UpdateClientUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ClientRepository.class);
        useCase = new UpdateClientUseCase(repository);
    }

    @Test
    void shouldUpdateWithoutChangingCpf() {
        var existing = new Client(1L, "Old", "123", "999");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        var dto = new ClientRequestDTO("New", "123", "888");

        var result = useCase.execute(1L, dto);

        assertAll(
                () -> assertEquals("New", result.nome()),
                () -> assertEquals("123", result.cpf()),
                () -> assertEquals("888", result.telefone())
        );
    }

    @Test
    void shouldUpdateWithNewCpf() {
        var existing = new Client(1L, "Old", "123", "999");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.existsByCpf("456")).thenReturn(false);
        when(repository.save(existing)).thenReturn(existing);

        var dto = new ClientRequestDTO("New", "456", "888");

        var result = useCase.execute(1L, dto);

        assertEquals("456", result.cpf());
    }

    @Test
    void shouldThrowWhenClientNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        var dto = new ClientRequestDTO("New", "123", "888");

        assertThrows(ClientNotFoundException.class,
                () -> useCase.execute(1L, dto));
    }

    @Test
    void shouldThrowWhenCpfAlreadyExists() {
        var existing = new Client(1L, "Old", "123", "999");

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.existsByCpf("456")).thenReturn(true);

        var dto = new ClientRequestDTO("New", "456", "888");

        assertThrows(ClientAlreadyExistsException.class,
                () -> useCase.execute(1L, dto));

        verify(repository, never()).save(any());
    }

}
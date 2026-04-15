package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.application.exception.clientException.ClientAlreadyExistsException;
import dev.zerphyis.schedule.application.exception.clientException.InvalidClientDataException;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateClientUseCaseTest {
    private ClientRepository repository;
    private CreateClientUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ClientRepository.class);
        useCase = new CreateClientUseCase(repository);
    }

    @Test
    void shouldCreateClientSuccessfully() {
        var dto = new ClientRequestDTO("Otavio", "123", "999");

        when(repository.existsByCpf("123")).thenReturn(false);
        when(repository.save(any())).thenAnswer(invocation -> {
            Client c = invocation.getArgument(0);
            c.setId(1L);
            return c;
        });

        var result = useCase.execute(dto);

        assertAll(
                () -> assertEquals("Otavio", result.nome()),
                () -> assertEquals("123", result.cpf()),
                () -> assertEquals("999", result.telefone())
        );

        verify(repository).existsByCpf("123");
        verify(repository).save(any());
    }

    @Test
    void shouldThrowWhenNameIsNull() {
        var dto = new ClientRequestDTO(null, "123", "999");

        assertThrows(InvalidClientDataException.class, () -> useCase.execute(dto));
        verifyNoInteractions(repository);
    }

    @Test
    void shouldThrowWhenNameIsBlank() {
        var dto = new ClientRequestDTO("   ", "123", "999");

        assertThrows(InvalidClientDataException.class, () -> useCase.execute(dto));
        verifyNoInteractions(repository);
    }

    @Test
    void shouldThrowWhenCpfIsNull() {
        var dto = new ClientRequestDTO("Otavio", null, "999");

        assertThrows(InvalidClientDataException.class, () -> useCase.execute(dto));
        verifyNoInteractions(repository);
    }

    @Test
    void shouldThrowWhenCpfIsBlank() {
        var dto = new ClientRequestDTO("Otavio", "   ", "999");

        assertThrows(InvalidClientDataException.class, () -> useCase.execute(dto));
        verifyNoInteractions(repository);
    }

    @Test
    void shouldThrowWhenCpfAlreadyExists() {
        var dto = new ClientRequestDTO("Otavio", "123", "999");

        when(repository.existsByCpf("123")).thenReturn(true);

        assertThrows(ClientAlreadyExistsException.class, () -> useCase.execute(dto));

        verify(repository).existsByCpf("123");
        verify(repository, never()).save(any());
    }
}
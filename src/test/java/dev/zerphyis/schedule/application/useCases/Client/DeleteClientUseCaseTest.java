package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.application.exception.clientException.ClientNotFoundException;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteClientUseCaseTest {
    private ClientRepository repository;
    private DeleteClientUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ClientRepository.class);
        useCase = new DeleteClientUseCase(repository);
    }

    @Test
    void shouldDeleteSuccessfully() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Client()));

        useCase.execute(1L);

        verify(repository).findById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void shouldThrowWhenClientNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> useCase.execute(1L));

        verify(repository).findById(1L);
        verify(repository, never()).deleteById(any());
    }
}
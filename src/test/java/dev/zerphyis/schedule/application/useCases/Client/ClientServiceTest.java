package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientResponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private CreateClientUseCase createUseCase;
    @Mock
    private FindByIdClientUseCase findByIdUseCase;
    @Mock
    private FindAllClientUseCase findAllUseCase;
    @Mock
    private UpdateClientUseCase updateUseCase;
    @Mock
    private DeleteClientUseCase deleteUseCase;

    @InjectMocks
    private ClientService service;

    @Test
    void shouldCreateClient() {
        var request = new ClientRequestDTO("Maria", "12345678901", "999999999");
        var response = new ClientResponseDTO(1L, "Maria", "12345678901", "999999999");

        when(createUseCase.execute(request)).thenReturn(response);

        var result = service.create(request);

        assertEquals(response, result);
        verify(createUseCase).execute(request);
    }

    @Test
    void shouldFindById() {
        var response = new ClientResponseDTO(1L, "Maria", "123", "999");

        when(findByIdUseCase.execute(1L)).thenReturn(response);

        var result = service.findById(1L);

        assertEquals(response, result);
        verify(findByIdUseCase).execute(1L);
    }

    @Test
    void shouldFindAll() {
        var list = List.of(new ClientResponseDTO(1L, "Maria", "123", "999"));

        when(findAllUseCase.execute()).thenReturn(list);

        var result = service.findAll();

        assertEquals(list, result);
        verify(findAllUseCase).execute();
    }

    @Test
    void shouldUpdate() {
        var request = new ClientRequestDTO("Maria", "123", "999");
        var response = new ClientResponseDTO(1L, "Maria", "123", "999");

        when(updateUseCase.execute(1L, request)).thenReturn(response);

        var result = service.update(1L, request);

        assertEquals(response, result);
        verify(updateUseCase).execute(1L, request);
    }

    @Test
    void shouldDelete() {
        service.delete(1L);

        verify(deleteUseCase).execute(1L);
    }
}

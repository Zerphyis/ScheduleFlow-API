package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;
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
class ProfessionalServiceTest {

    @Mock
    private CreateProfessionalUseCase createUseCase;
    @Mock
    private FindProfessionalByIdUseCase findByIdUseCase;
    @Mock
    private FindAllProfessionalUseCase findAllUseCase;
    @Mock
    private UpdateProfessionalUseCase updateUseCase;
    @Mock
    private DeleteProfessionalUseCase deleteUseCase;

    @InjectMocks
    private ProfessionalService service;

    @Test
    void shouldCreateProfessional() {
        var request = new ProfessionalRequestDTO("João", "Dentista", "joao@email.com");
        var response = new ProfessionalResponseDTO(1L, "João", "Dentista", "joao@email.com");

        when(createUseCase.execute(request)).thenReturn(response);

        var result = service.create(request);

        assertEquals(response, result);
        verify(createUseCase).execute(request);
    }

    @Test
    void shouldFindById() {
        var response = new ProfessionalResponseDTO(1L, "João", "Dentista", "email");

        when(findByIdUseCase.execute(1L)).thenReturn(response);

        var result = service.findById(1L);

        assertEquals(response, result);
        verify(findByIdUseCase).execute(1L);
    }

    @Test
    void shouldFindAll() {
        var list = List.of(new ProfessionalResponseDTO(1L, "João", "Dentista", "email"));

        when(findAllUseCase.execute()).thenReturn(list);

        var result = service.findAll();

        assertEquals(list, result);
        verify(findAllUseCase).execute();
    }

    @Test
    void shouldUpdate() {
        var request = new ProfessionalRequestDTO("João", "Dentista", "email");
        var response = new ProfessionalResponseDTO(1L, "João", "Dentista", "email");

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
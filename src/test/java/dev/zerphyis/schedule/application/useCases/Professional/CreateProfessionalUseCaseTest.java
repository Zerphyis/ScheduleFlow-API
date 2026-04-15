package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.application.exception.ProfessionalException.InvalidProfessionalDataException;
import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateProfessionalUseCaseTest {

    private ProfessionalRepository repository;
    private CreateProfessionalUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ProfessionalRepository.class);
        useCase = new CreateProfessionalUseCase(repository);
    }

    @Test
    void shouldCreateSuccessfully() {
        var dto = new ProfessionalRequestDTO("Otavio", "Dev", "email@email.com");

        when(repository.save(any())).thenAnswer(invocation -> {
            Professional p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });

        var result = useCase.execute(dto);

        assertAll(
                () -> assertEquals("Otavio", result.nome()),
                () -> assertEquals("Dev", result.especialidade()),
                () -> assertEquals("email@email.com", result.email())
        );

        verify(repository).save(any());
    }

    @Test
    void shouldThrowWhenNameInvalid() {
        var dto = new ProfessionalRequestDTO("", "Dev", "email");

        assertThrows(InvalidProfessionalDataException.class,
                () -> useCase.execute(dto));

        verifyNoInteractions(repository);
    }

    @Test
    void shouldThrowWhenEspecialidadeInvalid() {
        var dto = new ProfessionalRequestDTO("Otavio", "", "email");

        assertThrows(InvalidProfessionalDataException.class,
                () -> useCase.execute(dto));

        verifyNoInteractions(repository);
    }

    @Test
    void shouldThrowWhenEmailInvalid() {
        var dto = new ProfessionalRequestDTO("Otavio", "Dev", "");

        assertThrows(InvalidProfessionalDataException.class,
                () -> useCase.execute(dto));

        verifyNoInteractions(repository);
    }
}
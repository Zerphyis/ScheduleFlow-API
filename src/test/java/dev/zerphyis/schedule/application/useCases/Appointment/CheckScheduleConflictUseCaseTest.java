package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.appointmentException.ConflictException;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CheckScheduleConflictUseCaseTest {
    private AppointmentRepository repository;
    private CheckScheduleConflictUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(AppointmentRepository.class);
        useCase = new CheckScheduleConflictUseCase(repository);
    }

    @Test
    void shouldPassWhenNoConflict() {
        Long professionalId = 1L;
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);

        when(repository.existsByProfessionalIdAndDateTime(professionalId, dateTime))
                .thenReturn(false);

        assertDoesNotThrow(() ->
                useCase.execute(professionalId, dateTime)
        );

        verify(repository, times(1))
                .existsByProfessionalIdAndDateTime(professionalId, dateTime);
    }

    @Test
    void shouldThrowWhenConflictExists() {
        Long professionalId = 1L;
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);

        when(repository.existsByProfessionalIdAndDateTime(professionalId, dateTime))
                .thenReturn(true);

        ConflictException exception = assertThrows(
                ConflictException.class,
                () -> useCase.execute(professionalId, dateTime)
        );

        assertEquals(
                "O profissional já possui agendamento em: " + dateTime,
                exception.getMessage()
        );

        verify(repository, times(1))
                .existsByProfessionalIdAndDateTime(professionalId, dateTime);
    }
    }
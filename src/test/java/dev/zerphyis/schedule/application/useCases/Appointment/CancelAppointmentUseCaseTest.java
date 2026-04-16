package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.ProfessionalException.BusinessException;
import dev.zerphyis.schedule.application.exception.appointmentException.ResourceNotFoundException;
import dev.zerphyis.schedule.domain.entites.Appointment;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CancelAppointmentUseCaseTest {
    private AppointmentRepository repository;
    private CancelAppointmentUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(AppointmentRepository.class);
        useCase = new CancelAppointmentUseCase(repository);
    }

    @Test
    void shouldCancelSuccessfully() {
        Appointment ap = new Appointment();
        ap.setDateTime(LocalDateTime.now().plusDays(2));

        when(repository.findById(1L)).thenReturn(Optional.of(ap));

        useCase.execute(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void shouldThrowWhenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> useCase.execute(1L));
    }

    @Test
    void shouldThrowWhenLessThan24Hours() {
        Appointment ap = new Appointment();
        ap.setDateTime(LocalDateTime.now().plusHours(2));

        when(repository.findById(1L)).thenReturn(Optional.of(ap));

        assertThrows(BusinessException.class,
                () -> useCase.execute(1L));

        verify(repository, never()).deleteById(any());
    }

}
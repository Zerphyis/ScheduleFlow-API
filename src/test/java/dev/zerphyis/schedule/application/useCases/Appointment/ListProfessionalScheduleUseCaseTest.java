package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.appointmentException.ResourceNotFoundException;
import dev.zerphyis.schedule.domain.entites.Appointment;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListProfessionalScheduleUseCaseTest {
    private AppointmentRepository appointmentRepository;
    private ProfessionalRepository professionalRepository;
    private ListProfessionalScheduleUseCase useCase;

    @BeforeEach
    void setup() {
        appointmentRepository = mock(AppointmentRepository.class);
        professionalRepository = mock(ProfessionalRepository.class);

        useCase = new ListProfessionalScheduleUseCase(
                appointmentRepository,
                professionalRepository
        );
    }

    @Test
    void shouldReturnList() {
        var professional = new Professional(1L, "Prof", "Dev", "email");
        var client = new Client(1L, "Client", "123", "999");

        var ap = new Appointment();
        ap.setId(1L);
        ap.setProfessional(professional);
        ap.setClient(client);
        ap.setDateTime(LocalDateTime.now().plusDays(1));

        when(professionalRepository.findById(1L))
                .thenReturn(Optional.of(professional));

        when(appointmentRepository.findAllByProfessionalIdOrderByDateTimeAsc(1L))
                .thenReturn(List.of(ap));

        var result = useCase.execute(1L);

        assertEquals(1, result.size());
    }

    @Test
    void shouldThrowWhenNotFound() {
        when(professionalRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> useCase.execute(1L));

        verify(appointmentRepository, never()).findAllByProfessionalIdOrderByDateTimeAsc(any());
    }

    @Test
    void shouldReturnEmptyList() {
        when(professionalRepository.findById(1L))
                .thenReturn(Optional.of(new Professional()));

        when(appointmentRepository.findAllByProfessionalIdOrderByDateTimeAsc(1L))
                .thenReturn(List.of());

        var result = useCase.execute(1L);

        assertTrue(result.isEmpty());
    }

}
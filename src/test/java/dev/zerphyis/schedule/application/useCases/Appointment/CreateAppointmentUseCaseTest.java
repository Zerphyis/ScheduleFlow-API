package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.appointmentException.ResourceNotFoundException;
import dev.zerphyis.schedule.domain.entites.Appointment;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateAppointmentUseCaseTest {
    private AppointmentRepository appointmentRepository;
    private ProfessionalRepository professionalRepository;
    private ClientRepository clientRepository;

    private ValidateScheduleUseCase validateUseCase;
    private CheckScheduleConflictUseCase conflictUseCase;

    private CreateAppointmentUseCase useCase;

    @BeforeEach
    void setup() {
        appointmentRepository = mock(AppointmentRepository.class);
        professionalRepository = mock(ProfessionalRepository.class);
        clientRepository = mock(ClientRepository.class);

        validateUseCase = mock(ValidateScheduleUseCase.class);
        conflictUseCase = mock(CheckScheduleConflictUseCase.class);

        useCase = new CreateAppointmentUseCase(
                validateUseCase,
                conflictUseCase,
                professionalRepository,
                clientRepository,
                appointmentRepository
        );
    }

    @Test
    void shouldCreateSuccessfully() {
        var professional = new Professional(1L, "Prof", "Dev", "email");
        var client = new Client(1L, "Client", "123", "999");

        when(professionalRepository.findById(1L))
                .thenReturn(Optional.of(professional));
        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(client));

        when(appointmentRepository.save(any()))
                .thenAnswer(invocation -> {
                    Appointment a = invocation.getArgument(0);
                    a.setId(1L);
                    return a;
                });

        var dto = new AppointmentRequestDTO(
                1L, 1L, LocalDateTime.now().plusDays(1)
        );

        var result = useCase.execute(dto);

        assertNotNull(result);
        verify(validateUseCase).execute(any());
        verify(conflictUseCase).execute(eq(1L), any());
        verify(appointmentRepository).save(any());
    }

    @Test
    void shouldThrowWhenProfessionalNotFound() {
        when(professionalRepository.findById(1L))
                .thenReturn(Optional.empty());

        var dto = new AppointmentRequestDTO(1L, 1L, LocalDateTime.now());

        assertThrows(ResourceNotFoundException.class,
                () -> useCase.execute(dto));
    }

    @Test
    void shouldThrowWhenClientNotFound() {
        when(professionalRepository.findById(1L))
                .thenReturn(Optional.of(new Professional()));

        when(clientRepository.findById(1L))
                .thenReturn(Optional.empty());

        var dto = new AppointmentRequestDTO(1L, 1L, LocalDateTime.now());

        assertThrows(ResourceNotFoundException.class,
                () -> useCase.execute(dto));
    }

}
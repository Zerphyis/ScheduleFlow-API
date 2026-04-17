package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentResponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
    @Mock
    private CreateAppointmentUseCase createUseCase;
    @Mock
    private ListProfessionalScheduleUseCase listUseCase;
    @Mock
    private CancelAppointmentUseCase cancelUseCase;

    @InjectMocks
    private AppointmentService service;

    @Test
    void shouldCreateAppointment() {
        var request = new AppointmentRequestDTO(1L, 1L, LocalDateTime.now().plusDays(1));
        var response = new AppointmentResponseDTO(1L, "Dr João", "Maria", request.dateTime());

        when(createUseCase.execute(request)).thenReturn(response);

        var result = service.create(request);

        assertEquals(response, result);
        verify(createUseCase).execute(request);
    }

    @Test
    void shouldListByProfessional() {
        var list = List.of(new AppointmentResponseDTO(1L, "Dr João", "Maria", LocalDateTime.now()));

        when(listUseCase.execute(1L)).thenReturn(list);

        var result = service.listByProfessional(1L);

        assertEquals(list, result);
        verify(listUseCase).execute(1L);
    }

    @Test
    void shouldCancelAppointment() {
        service.cancel(1L);

        verify(cancelUseCase).execute(1L);
    }
}


package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentResponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class AppointmentService {

    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final ListProfessionalScheduleUseCase listProfessionalScheduleUseCase;
    private final CancelAppointmentUseCase cancelAppointmentUseCase;

    public AppointmentService(
            CreateAppointmentUseCase createAppointmentUseCase,
            ListProfessionalScheduleUseCase listProfessionalScheduleUseCase,
            CancelAppointmentUseCase cancelAppointmentUseCase
    ) {
        this.createAppointmentUseCase = createAppointmentUseCase;
        this.listProfessionalScheduleUseCase = listProfessionalScheduleUseCase;
        this.cancelAppointmentUseCase = cancelAppointmentUseCase;
    }

    @CacheEvict(value = "appointments", key = "#request.professionalId")
    public AppointmentResponseDTO create(AppointmentRequestDTO request) {
        return createAppointmentUseCase.execute(request);
    }

    @Cacheable(value = "appointments", key = "#professionalId")
    public List<AppointmentResponseDTO> listByProfessional(Long professionalId) {
        return listProfessionalScheduleUseCase.execute(professionalId);
    }

    @CacheEvict(value = "appointments", allEntries = true)
    public void cancel(Long appointmentId) {
        cancelAppointmentUseCase.execute(appointmentId);
    }
}
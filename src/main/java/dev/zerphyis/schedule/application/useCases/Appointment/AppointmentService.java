package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentReponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;

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

    public AppointmentReponseDTO create(AppointmentRequestDTO request) {
        return createAppointmentUseCase.execute(request);
    }

    public List<AppointmentReponseDTO> listByProfessional(Long professionalId) {
        return listProfessionalScheduleUseCase.execute(professionalId);
    }

    public void cancel(Long appointmentId) {
        cancelAppointmentUseCase.execute(appointmentId);
    }
}

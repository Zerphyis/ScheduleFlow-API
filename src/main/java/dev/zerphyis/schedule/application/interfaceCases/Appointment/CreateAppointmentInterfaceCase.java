package dev.zerphyis.schedule.application.interfaceCases.Appointment;

import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentResponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;

public interface CreateAppointmentInterfaceCase {
    AppointmentResponseDTO execute(AppointmentRequestDTO request);
}

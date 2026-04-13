package dev.zerphyis.schedule.application.interfaceCases.Appointment;

import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentReponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;

public interface CreateAppointmentInterfaceCase {
    AppointmentReponseDTO execute(AppointmentRequestDTO request);
}

package dev.zerphyis.schedule.application.interfaceCases.Appointment;

import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentResponseDTO;

import java.util.List;

public interface ListProfessionalScheduleInterfaceCase {

    List<AppointmentResponseDTO> execute(Long professionalId);
}

package dev.zerphyis.schedule.application.interfaceCases.Appointment;

import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentReponseDTO;

import java.util.List;

public interface ListProfessionalScheduleInterfaceCase {

    List<AppointmentReponseDTO> execute(Long professionalId);
}

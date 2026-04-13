package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.appointmentException.ConflictException;
import dev.zerphyis.schedule.application.interfaceCases.Appointment.CheckScheduleConflictInterfaceCase;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;

import java.time.LocalDateTime;

public class CheckScheduleConflictUseCase implements CheckScheduleConflictInterfaceCase {
    private final AppointmentRepository appointmentRepository;

    public CheckScheduleConflictUseCase(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void execute(Long professionalId, LocalDateTime dateTime) {

        boolean exists = appointmentRepository
                .existsByProfessionalIdAndDateTime(professionalId, dateTime);

        if (exists) {
            throw new ConflictException(
                    "Este horário já está ocupado para o profissional informado"
            );
        }
    }
}

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

    @Override
    public void execute(Long professionalId, LocalDateTime dateTime) {

        if (professionalId == null) {
            throw new IllegalArgumentException("professionalId não pode ser nulo");
        }

        if (dateTime == null) {
            throw new IllegalArgumentException("dateTime não pode ser nulo");
        }

        boolean exists = appointmentRepository
                .existsByProfessionalIdAndDateTime(professionalId, dateTime);

        if (exists) {
            throw new ConflictException(
                    "O profissional já possui agendamento em: " + dateTime
            );
        }
    }
}

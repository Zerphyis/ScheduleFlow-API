package dev.zerphyis.schedule.application.interfaceCases.Appointment;

import java.time.LocalDateTime;

public interface CheckScheduleConflictInterfaceCase {
    void execute(Long professionalId, LocalDateTime dateTime);
}

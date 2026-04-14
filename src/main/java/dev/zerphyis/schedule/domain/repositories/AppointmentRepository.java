package dev.zerphyis.schedule.domain.repositories;

import dev.zerphyis.schedule.domain.entites.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository {
    boolean existsByProfessionalIdAndDateTime(Long professionalId, LocalDateTime dateTime);
    Appointment save(Appointment appointment);
    List<Appointment> findAllByProfessionalIdOrderByDateTimeAsc(Long professionalId);

}

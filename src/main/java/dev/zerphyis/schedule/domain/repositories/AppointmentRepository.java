package dev.zerphyis.schedule.domain.repositories;

import dev.zerphyis.schedule.domain.entites.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    boolean existsByProfessionalIdAndDateTime(Long professionalId, LocalDateTime dateTime);

    List<Appointment> findAllByProfessionalIdOrderByDateTimeAsc(Long professionalId);

    Optional<Appointment> findById(Long id);

    void deleteById(Long id);

}

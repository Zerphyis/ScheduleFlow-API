package dev.zerphyis.schedule.infra.persistance.repository;

import dev.zerphyis.schedule.infra.persistance.entites.AppointmentJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;

public interface AppointmentRepositoryJpa extends JpaRepository<AppointmentJpa,Long> {
    boolean existsByProfessionalIdAndDateTime(Long professionalId, LocalDateTime dateTime);

}

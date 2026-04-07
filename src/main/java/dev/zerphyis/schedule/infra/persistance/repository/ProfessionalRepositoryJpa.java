package dev.zerphyis.schedule.infra.persistance.repository;

import dev.zerphyis.schedule.infra.persistance.entites.ProfessionalJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepositoryJpa extends JpaRepository<ProfessionalJpa,Long> {
}

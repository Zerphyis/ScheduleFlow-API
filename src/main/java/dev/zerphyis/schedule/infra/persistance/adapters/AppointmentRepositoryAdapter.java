package dev.zerphyis.schedule.infra.persistance.adapters;

import dev.zerphyis.schedule.domain.entites.Appointment;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;
import dev.zerphyis.schedule.infra.mappers.AppointmentMapper;
import dev.zerphyis.schedule.infra.persistance.entites.AppointmentJpa;
import dev.zerphyis.schedule.infra.persistance.repository.AppointmentRepositoryJpa;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentRepositoryAdapter implements AppointmentRepository {
    private final AppointmentRepositoryJpa jpaRepository;

    public AppointmentRepositoryAdapter(AppointmentRepositoryJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsByProfessionalIdAndDateTime(Long professionalId, LocalDateTime dateTime) {
        return jpaRepository.existsByProfessionalIdAndDateTime(professionalId, dateTime);
    }

    @Override
    public Appointment save(Appointment appointment) {
        AppointmentJpa entity = AppointmentMapper.toEntity(appointment);
        AppointmentJpa saved = jpaRepository.save(entity);
        return AppointmentMapper.toDomain(saved);
    }


}

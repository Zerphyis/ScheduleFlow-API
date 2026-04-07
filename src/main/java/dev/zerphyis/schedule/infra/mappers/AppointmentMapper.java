package dev.zerphyis.schedule.infra.mappers;

import dev.zerphyis.schedule.domain.entites.Appointment;
import dev.zerphyis.schedule.infra.persistance.entites.AppointmentJpa;

public class AppointmentMapper {

        public static Appointment toDomain(AppointmentJpa entity) {
            if (entity == null) return null;

            Appointment appointment = new Appointment();
            appointment.setId(entity.getId());
            appointment.setDateTime(entity.getDateTime());
            appointment.setProfessional(
                    ProfessionalMapper.toDomain(entity.getProfessional())
            );
            appointment.setClient(
                    ClientMapper.toDomain(entity.getClient())
            );

            return appointment;
        }

        public static AppointmentJpa toEntity(Appointment domain) {
            if (domain == null) return null;

            AppointmentJpa entity =
                    new AppointmentJpa();

            entity.setId(domain.getId());
            entity.setDateTime(domain.getDateTime());

            entity.setProfessional(
                    ProfessionalMapper.toEntity(domain.getProfessional())
            );

            entity.setClient(
                    ClientMapper.toEntity(domain.getClient())
            );

            return entity;
        }
    }


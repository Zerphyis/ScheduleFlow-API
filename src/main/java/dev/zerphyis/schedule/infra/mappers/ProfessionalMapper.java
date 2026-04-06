package dev.zerphyis.schedule.infra.mappers;

import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.infra.persistance.entites.ProfessionalJpa;

public class ProfessionalMapper {
        public static Professional toDomain(ProfessionalJpa entity) {
            if (entity == null) return null;

            return new Professional(
                    entity.getId(),
                    entity.getNome(),
                    entity.getEspecialidade(),
                    entity.getEmail()
            );
        }

        public static ProfessionalJpa toEntity(Professional domain) {
            if (domain == null) return null;

            return new ProfessionalJpa(
                    domain.getId(),
                    domain.getNome(),
                    domain.getEspecialidade(),
                    domain.getEmail()
            );
        }
    }


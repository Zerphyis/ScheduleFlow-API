package dev.zerphyis.schedule.infra.mappers;

import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.infra.persistance.entites.ProfessionalJpa;

public class ProfessionalMapper {

    public static Professional toDomain(ProfessionalJpa entity) {
        if (entity == null) return null;

        Professional professional = new Professional();
        professional.setId(entity.getId());
        professional.setNome(entity.getNome());
        professional.setEspecialidade(entity.getEspecialidade());
        professional.setEmail(entity.getEmail());

        return professional;
    }

    public static ProfessionalJpa toEntity(Professional domain) {
        if (domain == null) return null;

        ProfessionalJpa entity = new ProfessionalJpa();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setEspecialidade(domain.getEspecialidade());
        entity.setEmail(domain.getEmail());

        return entity;
    }
}
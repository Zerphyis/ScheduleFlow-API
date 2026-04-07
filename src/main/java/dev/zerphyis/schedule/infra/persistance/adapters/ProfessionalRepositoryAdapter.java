package dev.zerphyis.schedule.infra.persistance.adapters;

import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.persistance.entites.ProfessionalJpa;
import dev.zerphyis.schedule.infra.persistance.repository.ProfessionalRepositoryJpa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProfessionalRepositoryAdapter implements ProfessionalRepository {
    private final ProfessionalRepositoryJpa repository;

    public ProfessionalRepositoryAdapter(ProfessionalRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Professional save(Professional professional) {
        ProfessionalJpa entity = toJpa(professional);
        return toDomain(repository.save(entity));
    }

    @Override
    public Optional<Professional> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Professional> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private ProfessionalJpa toJpa(Professional professional) {
        ProfessionalJpa jpa = new ProfessionalJpa();
        jpa.setId(professional.getId());
        jpa.setNome(professional.getNome());
        jpa.setEspecialidade(professional.getEspecialidade());
        jpa.setEmail(professional.getEmail());
        return jpa;
    }

    private Professional toDomain(ProfessionalJpa jpa) {
        Professional professional = new Professional();
        professional.setId(jpa.getId());
        professional.setNome(jpa.getNome());
        professional.setEspecialidade(jpa.getEspecialidade());
        professional.setEmail(jpa.getEmail());
        return professional;
    }
}

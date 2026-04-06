package dev.zerphyis.schedule.domain.repositories;

import dev.zerphyis.schedule.domain.entites.Professional;

import java.util.List;
import java.util.Optional;

public interface ProfessionalRepository {
        Professional save(Professional professional);

        Optional<Professional> findById(Long id);

        List<Professional> findAll();

        void deleteById(Long id);
    }


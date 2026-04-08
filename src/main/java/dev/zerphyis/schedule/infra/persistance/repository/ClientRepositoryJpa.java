package dev.zerphyis.schedule.infra.persistance.repository;

import dev.zerphyis.schedule.infra.persistance.entites.ClientJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepositoryJpa extends JpaRepository<ClientJpa,Long> {

    boolean existsByCpf(String cpf);
}

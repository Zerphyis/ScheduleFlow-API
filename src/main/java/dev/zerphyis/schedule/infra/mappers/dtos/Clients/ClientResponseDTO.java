package dev.zerphyis.schedule.infra.mappers.dtos.Clients;

public record ClientResponseDTO(
        Long id,
        String nome,
        String cpf,
        String telefone) {
}

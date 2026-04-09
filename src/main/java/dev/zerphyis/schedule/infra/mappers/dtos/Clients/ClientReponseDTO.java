package dev.zerphyis.schedule.infra.mappers.dtos.Clients;

public record ClientReponseDTO(
        Long id,
        String nome,
        String cpf,
        String telefone) {
}

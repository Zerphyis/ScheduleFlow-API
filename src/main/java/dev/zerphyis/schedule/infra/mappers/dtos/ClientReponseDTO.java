package dev.zerphyis.schedule.infra.mappers.dtos;

public record ClientReponseDTO(
        Long id,
        String nome,
        String cpf,
        String telefone) {
}

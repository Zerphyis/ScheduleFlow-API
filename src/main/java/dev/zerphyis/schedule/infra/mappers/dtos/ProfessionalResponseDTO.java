package dev.zerphyis.schedule.infra.mappers.dtos;

public record ProfessionalResponseDTO(
        Long id,
        String nome,
        String especialidade,
        String email
) {
}

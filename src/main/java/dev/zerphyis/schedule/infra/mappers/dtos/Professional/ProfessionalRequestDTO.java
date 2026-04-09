package dev.zerphyis.schedule.infra.mappers.dtos.Professional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessionalRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Especialidade é obrigatória")
        String especialidade,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email) {
}

package dev.zerphyis.schedule.infra.mappers.dtos.Clients;

import jakarta.validation.constraints.NotBlank;

public record ClientRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone) {
}

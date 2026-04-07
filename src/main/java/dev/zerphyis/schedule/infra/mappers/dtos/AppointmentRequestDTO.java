package dev.zerphyis.schedule.infra.mappers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(

        @NotNull(message = "ID do profissional é obrigatório")
        Long professionalId,

        @NotNull(message = "ID do cliente é obrigatório")
        Long clientId,

        @NotNull(message = "Data é obrigatória")
        @Future(message = "A data deve ser futura")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dateTime) {
}

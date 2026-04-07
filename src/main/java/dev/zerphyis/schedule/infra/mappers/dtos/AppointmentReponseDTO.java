package dev.zerphyis.schedule.infra.mappers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AppointmentReponseDTO(
        Long id,
        String professionalName,
        String clientName,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dateTime
) {
}

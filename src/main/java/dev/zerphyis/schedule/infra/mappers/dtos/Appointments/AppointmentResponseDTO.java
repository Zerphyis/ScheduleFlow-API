package dev.zerphyis.schedule.infra.mappers.dtos.Appointments;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        String professionalName,
        String clientName,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dateTime
) {
}

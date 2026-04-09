package dev.zerphyis.schedule.infra.mappers.dtos;

import java.time.LocalDateTime;


public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp
) {
}

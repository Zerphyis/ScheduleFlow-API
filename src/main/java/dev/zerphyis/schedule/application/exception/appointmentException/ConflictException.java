package dev.zerphyis.schedule.application.exception.appointmentException;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}

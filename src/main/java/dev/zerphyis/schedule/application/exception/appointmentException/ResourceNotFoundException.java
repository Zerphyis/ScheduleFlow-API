package dev.zerphyis.schedule.application.exception.appointmentException;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

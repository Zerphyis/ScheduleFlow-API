package dev.zerphyis.schedule.application.exception.ProfessionalException;

public class ProfessionalNotFoundException extends RuntimeException {

    public ProfessionalNotFoundException(Long id) {
        super("Professional with id " + id + " not found");
    }
}

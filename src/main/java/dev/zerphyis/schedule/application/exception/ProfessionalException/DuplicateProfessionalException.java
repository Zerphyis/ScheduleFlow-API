package dev.zerphyis.schedule.application.exception.ProfessionalException;

public class DuplicateProfessionalException extends RuntimeException {

    public DuplicateProfessionalException(String email) {
        super("Professional with email " + email + " already exists");
    }
}

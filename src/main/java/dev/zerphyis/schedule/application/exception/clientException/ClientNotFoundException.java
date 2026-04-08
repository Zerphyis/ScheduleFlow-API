package dev.zerphyis.schedule.application.exception.clientException;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Cliente não encontrado com ID: " + id);
    }
}
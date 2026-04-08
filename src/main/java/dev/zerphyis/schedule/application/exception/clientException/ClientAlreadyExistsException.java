package dev.zerphyis.schedule.application.exception.clientException;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String cpf) {
        super("Já existe um cliente com o CPF: " + cpf);
    }
}
package dev.zerphyis.schedule.application.useCases.Client;

import dev.zerphyis.schedule.application.exception.clientException.ClientNotFoundException;
import dev.zerphyis.schedule.application.interfaceCases.Client.DeleteClientCaseInterface;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;

public class DeleteClientUseCase implements DeleteClientCaseInterface {
    private final ClientRepository repository;

    public DeleteClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }
    @Override
    public void execute(Long id) {

        if (repository.findById(id).isEmpty()) {
            throw new ClientNotFoundException(id);
        }

        repository.deleteById(id);
    }
}

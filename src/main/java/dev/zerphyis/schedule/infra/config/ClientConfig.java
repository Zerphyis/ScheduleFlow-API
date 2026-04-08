package dev.zerphyis.schedule.infra.config;

import dev.zerphyis.schedule.application.useCases.Client.*;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public CreateClientUseCase createClientUseCase(ClientRepository repository) {
        return new CreateClientUseCase(repository);
    }

    @Bean
    public FindByIdClientUseCase findClientByIdUseCase(ClientRepository repository) {
        return new FindByIdClientUseCase(repository);
    }

    @Bean
    public FindAllClientUseCase findAllClientsUseCase(ClientRepository repository) {
        return new FindAllClientUseCase(repository);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientRepository repository) {
        return new UpdateClientUseCase(repository);
    }

    @Bean
    public DeleteClientUseCase deleteClientUseCase(ClientRepository repository) {
        return new DeleteClientUseCase(repository);
    }


    @Bean
    public ClientService clientService(
            CreateClientUseCase createClientUseCase,
            FindByIdClientUseCase findClientByIdUseCase,
            FindAllClientUseCase findAllClientsUseCase,
            UpdateClientUseCase updateClientUseCase,
            DeleteClientUseCase deleteClientUseCase
    ) {
        return new ClientService(
                createClientUseCase,
                findClientByIdUseCase,
                findAllClientsUseCase,
                updateClientUseCase,
                deleteClientUseCase
        );
    }
}

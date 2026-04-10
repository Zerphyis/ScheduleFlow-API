package dev.zerphyis.schedule.infra.config;

import dev.zerphyis.schedule.application.useCases.Professional.*;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfessionalConfig {

        @Bean
        public CreateProfessionalUseCase createProfessionalUseCase(ProfessionalRepository repository) {
            return new CreateProfessionalUseCase(repository);
        }

        @Bean
        public FindProfessionalByIdUseCase findProfessionalByIdUseCase(ProfessionalRepository repository) {
            return new FindProfessionalByIdUseCase(repository);
        }

        @Bean
        public FindAllProfessionalUseCase findAllProfessionalsUseCase(ProfessionalRepository repository) {
            return new FindAllProfessionalUseCase(repository);
        }

        @Bean
        public UpdateProfessionalUseCase updateProfessionalUseCase(ProfessionalRepository repository) {
            return new UpdateProfessionalUseCase(repository);
        }

        @Bean
        public DeleteProfessionalUseCase deleteProfessionalUseCase(ProfessionalRepository repository) {
            return new DeleteProfessionalUseCase(repository);
        }


        @Bean
        public ProfessionalService professionalFacadeService(
                CreateProfessionalUseCase createUseCase,
                FindProfessionalByIdUseCase findByIdUseCase,
                FindAllProfessionalUseCase findAllUseCase,
                UpdateProfessionalUseCase updateUseCase,
                DeleteProfessionalUseCase deleteUseCase
        ) {
            return new ProfessionalService(
                    createUseCase,
                    findByIdUseCase,
                    findAllUseCase,
                    updateUseCase,
                    deleteUseCase
            );
        }
    }


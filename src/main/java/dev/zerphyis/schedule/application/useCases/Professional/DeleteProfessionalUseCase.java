package dev.zerphyis.schedule.application.useCases.Professional;

import dev.zerphyis.schedule.application.exception.ProfessionalException.ProfessionalNotFoundException;
import dev.zerphyis.schedule.application.interfaceCases.Professional.DeleteProfessionalCaseInterface;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;

public class DeleteProfessionalUseCase implements DeleteProfessionalCaseInterface {

        private final ProfessionalRepository repository;

        public DeleteProfessionalUseCase(ProfessionalRepository repository) {
            this.repository = repository;
        }

        public void execute(Long id) {

            if (!repository.findById(id).isPresent()) {
                throw new ProfessionalNotFoundException(id);
            }

            repository.deleteById(id);
        }
    }


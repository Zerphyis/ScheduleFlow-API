package dev.zerphyis.schedule.infra.config;

import dev.zerphyis.schedule.application.interfaceCases.Appointment.*;
import dev.zerphyis.schedule.application.useCases.Appointment.*;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentConfig {
    @Bean
    public ValidateScheduleUseCase validateScheduleUseCase() {
        return new ValidateScheduleUseCase();
    }

    @Bean
    public CheckScheduleConflictUseCase checkScheduleConflictUseCase(
            AppointmentRepository appointmentRepository
    ) {
        return new CheckScheduleConflictUseCase(appointmentRepository);
    }

    @Bean
    public CreateAppointmentUseCase createAppointmentUseCase(
            ValidateScheduleUseCase validateScheduleUseCase,
            CheckScheduleConflictUseCase checkScheduleConflictUseCase,
            ProfessionalRepository professionalRepository,
            ClientRepository clientRepository,
            AppointmentRepository appointmentRepository
    ) {
        return new CreateAppointmentUseCase(
                validateScheduleUseCase,
                checkScheduleConflictUseCase,
                professionalRepository,
                clientRepository,
                appointmentRepository
        );
    }

    @Bean
    public ListProfessionalScheduleUseCase listProfessionalScheduleUseCase(
            AppointmentRepository appointmentRepository,
            ProfessionalRepository professionalRepository
    ) {
        return new ListProfessionalScheduleUseCase(
                appointmentRepository,
                professionalRepository
        );
    }

    @Bean
    public CancelAppointmentUseCase cancelAppointmentUseCase(
            AppointmentRepository appointmentRepository
    ) {
        return new CancelAppointmentUseCase(appointmentRepository);
    }

    @Bean
    public AppointmentService appointmentService(
            CreateAppointmentUseCase createAppointmentUseCase,
            ListProfessionalScheduleUseCase listProfessionalScheduleUseCase,
            CancelAppointmentUseCase cancelAppointmentUseCase
    ) {
        return new AppointmentService(
                createAppointmentUseCase,
                listProfessionalScheduleUseCase,
                cancelAppointmentUseCase
        );
    }
}
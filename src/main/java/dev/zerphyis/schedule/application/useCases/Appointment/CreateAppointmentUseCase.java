package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.appointmentException.ResourceNotFoundException;
import dev.zerphyis.schedule.application.interfaceCases.Appointment.CreateAppointmentInterfaceCase;
import dev.zerphyis.schedule.domain.entites.Appointment;
import dev.zerphyis.schedule.domain.entites.Client;
import dev.zerphyis.schedule.domain.entites.Professional;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;
import dev.zerphyis.schedule.domain.repositories.ClientRepository;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentReponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;

public class CreateAppointmentUseCase implements CreateAppointmentInterfaceCase {
    private final ValidateScheduleUseCase validateScheduleUseCase;
    private final CheckScheduleConflictUseCase checkScheduleConflictUseCase;
    private final ProfessionalRepository professionalRepository;
    private final ClientRepository clientRepository;
    private final AppointmentRepository appointmentRepository;

    public CreateAppointmentUseCase(ValidateScheduleUseCase validateScheduleUseCase, CheckScheduleConflictUseCase checkScheduleConflictUseCase, ProfessionalRepository professionalRepository, ClientRepository clientRepository, AppointmentRepository appointmentRepository) {
        this.validateScheduleUseCase = validateScheduleUseCase;
        this.checkScheduleConflictUseCase = checkScheduleConflictUseCase;
        this.professionalRepository = professionalRepository;
        this.clientRepository = clientRepository;
        this.appointmentRepository = appointmentRepository;
    }




    @Override
    public AppointmentReponseDTO execute(AppointmentRequestDTO request) {

        Professional professional = professionalRepository.findById(request.professionalId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profissional não encontrado")
                );

        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente não encontrado")
                );

        validateScheduleUseCase.execute(request.dateTime());

        checkScheduleConflictUseCase.execute(
                request.professionalId(),
                request.dateTime()
        );

        Appointment appointment = new Appointment();
        appointment.setProfessional(professional);
        appointment.setClient(client);
        appointment.setDateTime(request.dateTime());

        Appointment saved = appointmentRepository.save(appointment);

        return new AppointmentReponseDTO(
                saved.getId(),
                saved.getProfessional().getNome(),
                saved.getClient().getNome(),
                saved.getDateTime()
        );
    }
}

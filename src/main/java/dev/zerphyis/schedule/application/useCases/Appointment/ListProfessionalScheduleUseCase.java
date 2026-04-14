package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.appointmentException.ResourceNotFoundException;
import dev.zerphyis.schedule.application.interfaceCases.Appointment.ListProfessionalScheduleInterfaceCase;
import dev.zerphyis.schedule.domain.entites.Appointment;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;
import dev.zerphyis.schedule.domain.repositories.ProfessionalRepository;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentReponseDTO;

import java.util.List;

public class ListProfessionalScheduleUseCase implements ListProfessionalScheduleInterfaceCase {
    private final AppointmentRepository appointmentRepository;
    private final ProfessionalRepository professionalRepository;

    public ListProfessionalScheduleUseCase(
            AppointmentRepository appointmentRepository,
            ProfessionalRepository professionalRepository
    ) {
        this.appointmentRepository = appointmentRepository;
        this.professionalRepository = professionalRepository;
    }

    @Override
    public List<AppointmentReponseDTO> execute(Long professionalId) {

        professionalRepository.findById(professionalId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profissional não encontrado")
                );

        List<Appointment> appointments =
                appointmentRepository.findAllByProfessionalIdOrderByDateTimeAsc(professionalId);

        return appointments.stream()
                .map(appointment -> new AppointmentReponseDTO(
                        appointment.getId(),
                        appointment.getProfessional().getNome(),
                        appointment.getClient().getNome(),
                        appointment.getDateTime()
                ))
                .toList();
    }
}

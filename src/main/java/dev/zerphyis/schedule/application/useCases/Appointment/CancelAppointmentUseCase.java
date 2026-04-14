package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.ProfessionalException.BusinessException;
import dev.zerphyis.schedule.application.exception.appointmentException.ResourceNotFoundException;
import dev.zerphyis.schedule.application.interfaceCases.Appointment.CancelAppointmentInterfaceCase;
import dev.zerphyis.schedule.domain.entites.Appointment;
import dev.zerphyis.schedule.domain.repositories.AppointmentRepository;

import java.time.LocalDateTime;

public class CancelAppointmentUseCase implements CancelAppointmentInterfaceCase {
    private final AppointmentRepository appointmentRepository;

    public CancelAppointmentUseCase(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void execute(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agendamento não encontrado")
                );

        LocalDateTime limit = LocalDateTime.now().plusHours(24);

        if (appointment.getDateTime().isBefore(limit)) {
            throw new BusinessException(
                    "Cancelamento permitido apenas com 24 horas de antecedência"
            );
        }

        appointmentRepository.deleteById(appointmentId);
    }
}

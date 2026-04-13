package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.ProfessionalException.BusinessException;
import dev.zerphyis.schedule.application.interfaceCases.Appointment.ValidateScheduleInterfaceCase;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ValidateScheduleUseCase implements ValidateScheduleInterfaceCase {

    public void execute(LocalDateTime dateTime) {

        if (dateTime == null) {
            throw new BusinessException("Data do agendamento não pode ser nula");
        }

        validateDay(dateTime);
        validateBusinessHours(dateTime);
        validateAdvanceTime(dateTime);
    }

    private void validateDay(LocalDateTime dateTime) {
        if (dateTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new BusinessException("Agendamentos não são permitidos aos domingos");
        }
    }

    private void validateBusinessHours(LocalDateTime dateTime) {
        int hour = dateTime.getHour();

        if (hour < 8 || hour >= 18) {
            throw new BusinessException("Horário permitido é entre 08:00 e 18:00");
        }
    }

    private void validateAdvanceTime(LocalDateTime dateTime) {
        LocalDateTime nowPlus30 = LocalDateTime.now().plusMinutes(30);

        if (dateTime.isBefore(nowPlus30)) {
            throw new BusinessException(
                    "O agendamento deve ter no mínimo 30 minutos de antecedência"
            );
        }
    }
}

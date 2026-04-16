package dev.zerphyis.schedule.application.useCases.Appointment;

import dev.zerphyis.schedule.application.exception.ProfessionalException.BusinessException;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidateScheduleUseCaseTest {
    private final ValidateScheduleUseCase useCase = new ValidateScheduleUseCase();

    @Test
    void shouldPassValidation() {
        LocalDateTime validDate = LocalDateTime.of(2026, 4, 20, 10, 0); // segunda-feira

        assertDoesNotThrow(() -> useCase.execute(validDate));
    }

    @Test
    void shouldThrowWhenDateIsNull() {
        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> useCase.execute(null)
        );

        assertNotNull(exception.getMessage());
    }

    @Test
    void shouldThrowWhenSunday() {
        LocalDateTime sunday = LocalDateTime.of(2026, 4, 19, 10, 0);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> useCase.execute(sunday)
        );

        assertNotNull(exception.getMessage());
    }

    @Test
    void shouldThrowWhenOutsideBusinessHours() {
        LocalDateTime invalid = LocalDateTime.of(2026, 4, 20, 7, 0);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> useCase.execute(invalid)
        );

        assertNotNull(exception.getMessage());
    }

    @Test
    void shouldThrowWhenLessThan30Minutes() {
        LocalDateTime invalid = LocalDateTime.now().plusMinutes(10);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> useCase.execute(invalid)
        );

        assertNotNull(exception.getMessage());
    }
}
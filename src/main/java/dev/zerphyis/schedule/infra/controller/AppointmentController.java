package dev.zerphyis.schedule.infra.controller;

import dev.zerphyis.schedule.application.useCases.Appointment.AppointmentService;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentResponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(
            @RequestBody @Valid AppointmentRequestDTO request
    ) {
        AppointmentResponseDTO response = appointmentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<AppointmentResponseDTO>> listByProfessional(
            @PathVariable Long professionalId
    ) {
        List<AppointmentResponseDTO> response =
                appointmentService.listByProfessional(professionalId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> cancel(
            @PathVariable Long appointmentId
    ) {
        appointmentService.cancel(appointmentId);
        return ResponseEntity.noContent().build();
    }
}

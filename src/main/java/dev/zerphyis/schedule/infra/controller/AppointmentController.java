package dev.zerphyis.schedule.infra.controller;

import dev.zerphyis.schedule.application.useCases.Appointment.AppointmentService;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentResponseDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Appointments", description = "Gerenciamento de agendamentos")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Criar um agendamento")
    @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso")
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(
            @RequestBody @Valid AppointmentRequestDTO request
    ) {
        AppointmentResponseDTO response = appointmentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Listar agendamentos por profissional")
    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<AppointmentResponseDTO>> listByProfessional(
            @PathVariable Long professionalId
    ) {
        return ResponseEntity.ok(
                appointmentService.listByProfessional(professionalId)
        );
    }

    @Operation(summary = "Cancelar um agendamento")
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> cancel(@PathVariable Long appointmentId) {
        appointmentService.cancel(appointmentId);
        return ResponseEntity.noContent().build();
    }
}

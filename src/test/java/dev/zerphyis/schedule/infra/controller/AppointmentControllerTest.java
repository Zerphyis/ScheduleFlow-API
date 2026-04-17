package dev.zerphyis.schedule.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zerphyis.schedule.application.useCases.Appointment.AppointmentService;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Appointments.AppointmentResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAppointment() throws Exception {
        var request = new AppointmentRequestDTO(1L, 1L, LocalDateTime.now().plusDays(1));
        var response = new AppointmentResponseDTO(1L, "Dr João", "Maria", request.dateTime());

        when(service.create(any())).thenReturn(response);

        mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void shouldListByProfessional() throws Exception {
        when(service.listByProfessional(1L)).thenReturn(List.of());

        mockMvc.perform(get("/appointments/professional/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCancelAppointment() throws Exception {
        mockMvc.perform(delete("/appointments/1"))
                .andExpect(status().isNoContent());

        verify(service).cancel(1L);
    }
}
package dev.zerphyis.schedule.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zerphyis.schedule.application.useCases.Professional.ProfessionalService;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Professional.ProfessionalResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProfessionalController.class)
class ProfessionalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessionalService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateProfessional() throws Exception {
        var request = new ProfessionalRequestDTO("João", "Dentista", "email@email.com");
        var response = new ProfessionalResponseDTO(1L, "João", "Dentista", "email@email.com");

        when(service.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/professionals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("João"));
    }

    @Test
    void shouldFindById() throws Exception {
        when(service.findById(1L))
                .thenReturn(new ProfessionalResponseDTO(1L, "João", "Dentista", "email"));

        mockMvc.perform(get("/api/professionals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/api/professionals/1"))
                .andExpect(status().isNoContent());

        verify(service).delete(1L);
    }
}
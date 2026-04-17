package dev.zerphyis.schedule.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zerphyis.schedule.application.useCases.Client.ClientService;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientRequestDTO;
import dev.zerphyis.schedule.infra.mappers.dtos.Clients.ClientResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateClient() throws Exception {
        var request = new ClientRequestDTO("Maria", "12345678901", "999999999");
        var response = new ClientResponseDTO(1L, "Maria", "12345678901", "999999999");

        when(service.create(any())).thenReturn(response);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Maria"));
    }

    @Test
    void shouldFindAll() throws Exception {
        when(service.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk());
    }
}
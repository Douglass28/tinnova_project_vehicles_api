package tinnova.test.com.example.demo.application.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import tinnova.test.com.example.demo.ControllerIntegrationTestBase;
import tinnova.test.com.example.demo.builders.controller.CreateVehiclePayloadBuilder;
import tinnova.test.com.example.demo.builders.controller.PatchVehiclePayloadBuilder;
import tinnova.test.com.example.demo.builders.controller.UpdateVehiclePayloadBuilder;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;

@DisplayName("VehicleController Security IT")
class VehicleControllerSecurityIT extends ControllerIntegrationTestBase {

    @Test
    @DisplayName("Sem token deve retornar 403 para endpoints protegidos")
    void shouldReturnUnauthorizedWithoutToken() throws Exception {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().build());

        mockMvc.perform(get("/api/v1/veiculos")).andExpect(status().isForbidden());
        mockMvc.perform(post("/api/v1/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CreateVehiclePayloadBuilder.create().build())))
            .andExpect(status().isForbidden());
        mockMvc.perform(put("/api/v1/veiculos/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UpdateVehiclePayloadBuilder.create().build())))
            .andExpect(status().isForbidden());
        mockMvc.perform(patch("/api/v1/veiculos/{id}", entity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PatchVehiclePayloadBuilder.create().build())))
            .andExpect(status().isForbidden());
        mockMvc.perform(delete("/api/v1/veiculos/{id}", entity.getId()))
            .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("USER deve ter 403 para POST PUT PATCH DELETE")
    void shouldForbidWriteMethodsForUser() throws Exception {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().build());
        String userToken = userToken();

        mockMvc.perform(post("/api/v1/veiculos")
                .header("Authorization", "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CreateVehiclePayloadBuilder.create().build())))
            .andExpect(status().isForbidden());

        mockMvc.perform(put("/api/v1/veiculos/{id}", entity.getId())
                .header("Authorization", "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UpdateVehiclePayloadBuilder.create().build())))
            .andExpect(status().isForbidden());

        mockMvc.perform(patch("/api/v1/veiculos/{id}", entity.getId())
                .header("Authorization", "Bearer " + userToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PatchVehiclePayloadBuilder.create().build())))
            .andExpect(status().isForbidden());

        mockMvc.perform(delete("/api/v1/veiculos/{id}", entity.getId())
                .header("Authorization", "Bearer " + userToken))
            .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("USER e ADMIN devem acessar GET com 200")
    void shouldAllowGetForUserAndAdmin() throws Exception {
        vehicleRepository.save(VehicleEntityBuilder.create().build());
        String userToken = userToken();
        String adminToken = adminToken();

        mockMvc.perform(get("/api/v1/veiculos")
                .header("Authorization", "Bearer " + userToken))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/veiculos")
                .header("Authorization", "Bearer " + adminToken))
            .andExpect(status().isOk());
    }
}

package tinnova.test.com.example.demo.application.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import tinnova.test.com.example.demo.ControllerIntegrationTestBase;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import tinnova.test.com.example.demo.builders.controller.CreateVehiclePayloadBuilder;
import tinnova.test.com.example.demo.builders.controller.PatchVehiclePayloadBuilder;
import tinnova.test.com.example.demo.builders.controller.UpdateVehiclePayloadBuilder;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;

@DisplayName("VehicleController Write IT")
class VehicleControllerWriteIT extends ControllerIntegrationTestBase {

    @Test
    @DisplayName("POST /veiculos deve retornar 201 para ADMIN")
    void shouldCreateVehicleForAdmin() throws Exception {
        String token = adminToken();
        String body = objectMapper.writeValueAsString(
            CreateVehiclePayloadBuilder.create()
                .withPrice(new BigDecimal("10000.00"))
                .build()
        );

        mockMvc.perform(post("/api/v1/veiculos")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.price").value(2000.00));
    }

    @Test
    @DisplayName("POST /veiculos duplicado deve retornar 409")
    void shouldReturnConflictWhenDuplicateChassis() throws Exception {
        String token = adminToken();
        vehicleRepository.save(VehicleEntityBuilder.create().withChassis("23955678901234590").withPlate("AAA1234").build());

        String body = objectMapper.writeValueAsString(
            CreateVehiclePayloadBuilder.create()
                .withChassis("23955678901234590")
                .withPlate("BBB1234")
                .build()
        );

        mockMvc.perform(post("/api/v1/veiculos")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("PUT /veiculos/{id} deve retornar 200 para ADMIN")
    void shouldUpdateVehicleForAdmin() throws Exception {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().build());
        String token = adminToken();
        String body = objectMapper.writeValueAsString(
            UpdateVehiclePayloadBuilder.create().withBrand("Honda").withModel("Civic").build()
        );

        mockMvc.perform(put("/api/v1/veiculos/{id}", entity.getId())
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.brand").value("Honda"))
            .andExpect(jsonPath("$.model").value("Civic"));
    }

    @Test
    @DisplayName("PATCH /veiculos/{id} deve retornar 204 para ADMIN")
    void shouldPatchVehicleForAdmin() throws Exception {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Ford").build());
        String token = adminToken();
        String body = objectMapper.writeValueAsString(
            PatchVehiclePayloadBuilder.create().withBrand("BMW").build()
        );

        mockMvc.perform(patch("/api/v1/veiculos/{id}", entity.getId())
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("PATCH /veiculos/{id} vazio deve retornar 400")
    void shouldReturnBadRequestForEmptyPatch() throws Exception {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().build());
        String token = adminToken();
        String body = objectMapper.writeValueAsString(PatchVehiclePayloadBuilder.create().withBrand(null).build());

        mockMvc.perform(patch("/api/v1/veiculos/{id}", entity.getId())
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE /veiculos/{id} deve retornar 204 e fazer soft delete")
    void shouldSoftDeleteForAdmin() throws Exception {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().withStatus(VehicleStatus.ACTIVE).build());
        String token = adminToken();

        mockMvc.perform(delete("/api/v1/veiculos/{id}", entity.getId())
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isNoContent());

        var persisted = vehicleRepository.findById(entity.getId()).orElseThrow();
        org.junit.jupiter.api.Assertions.assertEquals(VehicleStatus.DELETED, persisted.getStatus());
    }
}

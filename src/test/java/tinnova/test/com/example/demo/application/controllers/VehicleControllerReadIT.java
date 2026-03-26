package tinnova.test.com.example.demo.application.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tinnova.test.com.example.demo.ControllerIntegrationTestBase;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;

@DisplayName("VehicleController Read IT")
class VehicleControllerReadIT extends ControllerIntegrationTestBase {

    @Test
    @DisplayName("GET /veiculos deve retornar 200 para USER")
    void shouldAllowUserToListVehicles() throws Exception {
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Ford").build());
        String token = userToken();

        mockMvc.perform(get("/api/v1/veiculos")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].brand").exists());
    }

    @Test
    @DisplayName("GET /veiculos/{id} deve retornar 200 para ADMIN")
    void shouldAllowAdminToRetrieveById() throws Exception {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Toyota").build());
        String token = adminToken();

        mockMvc.perform(get("/api/v1/veiculos/{id}", entity.getId())
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(entity.getId().toString()))
            .andExpect(jsonPath("$.brand").value("Toyota"));
    }

    @Test
    @DisplayName("GET /veiculos/{id} inexistente deve retornar 404")
    void shouldReturnNotFoundForUnknownId() throws Exception {
        String token = userToken();
        mockMvc.perform(get("/api/v1/veiculos/{id}", java.util.UUID.randomUUID())
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /veiculos/relatorios/por-marca deve contar apenas ativos")
    void shouldReturnBrandReportForActiveOnly() throws Exception {
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Ford").withStatus(VehicleStatus.ACTIVE).build());
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("Ford").withStatus(VehicleStatus.ACTIVE).build());
        vehicleRepository.save(VehicleEntityBuilder.create().withBrand("BMW").withStatus(VehicleStatus.DELETED).build());

        String token = userToken();
        mockMvc.perform(get("/api/v1/veiculos/relatorios/por-marca")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[?(@.brand=='Ford')].count").value(org.hamcrest.Matchers.hasItem(2)));
    }
}

package tinnova.test.com.example.demo.application.usecases.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import tinnova.test.com.example.demo.UseCaseIntegrationTestBase;
import tinnova.test.com.example.demo.application.usecases.vehicle.patch.PatchVehicleUseCase;
import tinnova.test.com.example.demo.builders.usecases.PatchVehicleInputBuilder;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;

@DisplayName("PatchVehicleTest - integração com Postgres real")
class PatchVehicleTest extends UseCaseIntegrationTestBase {

    @Autowired
    private PatchVehicleUseCase patchVehicleUseCase;

    @Test
    @DisplayName("Deve atualizar parcialmente os campos enviados")
    void shouldPatchPartially() {
        var entity = vehicleRepository.save(
            VehicleEntityBuilder.create().withBrand("Ford").withModel("Focus").build()
        );

        var response = patchVehicleUseCase.execute(
            PatchVehicleInputBuilder.create()
                .withId(entity.getId())
                .withAllFieldsNull()
                .withBrand("BMW")
                .build()
        );

        assertEquals("BMW", response.getBrand());
        assertEquals("Focus", response.getModel());
    }

    @Test
    @DisplayName("Deve retornar bad request quando payload vier vazio")
    void shouldReturnBadRequestForEmptyPayload() {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().build());

        ResponseStatusException exception = assertThrowsExactly(
            ResponseStatusException.class,
            () -> patchVehicleUseCase.execute(
                PatchVehicleInputBuilder.create().withId(entity.getId()).withAllFieldsNull().build()
            )
        );

        assertEquals(400, exception.getStatusCode().value());
        assertTrue(exception.getReason().contains("Informe ao menos um campo"));
    }

    @Test
    @DisplayName("Deve retornar not found para id inexistente")
    void shouldReturnNotFoundForUnknownId() {
        ResponseStatusException exception = assertThrowsExactly(
            ResponseStatusException.class,
            () -> patchVehicleUseCase.execute(
                PatchVehicleInputBuilder.create().withId(UUID.randomUUID()).withBrand("BMW").build()
            )
        );

        assertEquals(404, exception.getStatusCode().value());
    }
}

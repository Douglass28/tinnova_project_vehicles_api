package tinnova.test.com.example.demo.application.usecases.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import tinnova.test.com.example.demo.UseCaseIntegrationTestBase;
import tinnova.test.com.example.demo.application.usecases.vehicle.retrievebyid.RetrieveVehicleByIdUseCase;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;

@DisplayName("RetrieveVehicleByIdTest - integração com Postgres real")
class RetrieveVehicleByIdTest extends UseCaseIntegrationTestBase {

    @Autowired
    private RetrieveVehicleByIdUseCase retrieveVehicleByIdUseCase;

    @Test
    @DisplayName("Deve recuperar veículo por id existente")
    void shouldRetrieveById() {
        var entity = vehicleRepository.save(
            VehicleEntityBuilder.create().withBrand("Toyota").withModel("Corolla").build()
        );

        var response = retrieveVehicleByIdUseCase.execute(entity.getId());

        assertEquals(entity.getId(), response.getId());
        assertEquals("Toyota", response.getBrand());
        assertEquals("Corolla", response.getModel());
    }

    @Test
    @DisplayName("Deve retornar not found para id inexistente")
    void shouldThrowNotFound() {
        ResponseStatusException exception = assertThrowsExactly(
            ResponseStatusException.class,
            () -> retrieveVehicleByIdUseCase.execute(UUID.randomUUID())
        );
        assertEquals(404, exception.getStatusCode().value());
    }
}

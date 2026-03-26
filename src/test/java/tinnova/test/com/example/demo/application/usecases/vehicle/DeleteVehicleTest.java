package tinnova.test.com.example.demo.application.usecases.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import tinnova.test.com.example.demo.UseCaseIntegrationTestBase;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import tinnova.test.com.example.demo.application.usecases.vehicle.delete.DeleteVehicleUseCase;
import tinnova.test.com.example.demo.builders.usecases.VehicleEntityBuilder;

@DisplayName("DeleteVehicleTest - integração com Postgres real")
class DeleteVehicleTest extends UseCaseIntegrationTestBase {

    @Autowired
    private DeleteVehicleUseCase deleteVehicleUseCase;

    @Test
    @DisplayName("Deve realizar soft delete com sucesso")
    void shouldSoftDeleteVehicle() {
        var entity = vehicleRepository.save(VehicleEntityBuilder.create().build());

        deleteVehicleUseCase.execute(entity.getId());

        var persisted = vehicleRepository.findById(entity.getId()).orElseThrow();
        assertEquals(VehicleStatus.DELETED, persisted.getStatus());
    }

    @Test
    @DisplayName("Deve retornar not found para id inexistente")
    void shouldThrowNotFoundWhenIdDoesNotExist() {
        ResponseStatusException exception = assertThrowsExactly(
            ResponseStatusException.class,
            () -> deleteVehicleUseCase.execute(UUID.randomUUID())
        );

        assertEquals(404, exception.getStatusCode().value());
    }
}

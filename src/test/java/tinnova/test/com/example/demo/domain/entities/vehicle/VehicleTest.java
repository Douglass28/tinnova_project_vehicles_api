package tinnova.test.com.example.demo.domain.entities.vehicle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import tinnova.test.com.example.demo.builders.domain.VehicleDataBuilder;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleData;
import tinnova.test.com.example.demo.domain.exceptions.BusinessException;

@DisplayName("Vehicle Domain - Testes de unidade")
class VehicleTest {

    @Test
    @DisplayName("Deve criar veiculo com sucesso quando dados forem validos")
    void shouldCreateVehicleSuccessfully() {
        VehicleData vehicleData = VehicleDataBuilder.create().build();

        Vehicle vehicle = Vehicle.create(vehicleData);

        assertNotNull(vehicle);
        assertNotNull(vehicle.getId());
        assertNotNull(vehicle.getCreatedAt());
        assertNotNull(vehicle.getUpdatedAt());
        assertEquals(VehicleStatus.ACTIVE, vehicle.getStatus());
        assertEquals(vehicleData.getChassis(), vehicle.getChassis());
        assertEquals(vehicleData.getPlate(), vehicle.getPlate());
        assertEquals(vehicleData.getBrand(), vehicle.getBrand());
        assertEquals(vehicleData.getModel(), vehicle.getModel());
        assertEquals(vehicleData.getYear(), vehicle.getYear());
        assertEquals(vehicleData.getColor(), vehicle.getColor());
        assertEquals(0, vehicleData.getPrice().compareTo(vehicle.getPrice()));
        assertEquals(vehicleData.getType(), vehicle.getType());
        assertTrue(!vehicle.getUpdatedAt().isBefore(vehicle.getCreatedAt()));
    }

    @Test
    @DisplayName("Deve rebuildar veiculo com id e status recebidos")
    void shouldRebuildVehicleSuccessfully() {
        UUID existingId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        VehicleData vehicleData = VehicleDataBuilder.create().withBrand("Chevrolet").build();

        Vehicle vehicle = Vehicle.rebuild(existingId, createdAt, updatedAt, VehicleStatus.DELETED, vehicleData);

        assertEquals(existingId, vehicle.getId());
        assertEquals(createdAt, vehicle.getCreatedAt());
        assertEquals(updatedAt, vehicle.getUpdatedAt());
        assertEquals(VehicleStatus.DELETED, vehicle.getStatus());
        assertEquals("Chevrolet", vehicle.getBrand());
    }

    @Test
    @DisplayName("Deve lancar excecao quando price for menor ou igual a zero")
    void shouldThrowExceptionWhenPriceIsZero() {
        VehicleData vehicleData = VehicleDataBuilder.create().withPrice(BigDecimal.ZERO).build();

        BusinessException exception =
            assertThrowsExactly(BusinessException.class, () -> Vehicle.create(vehicleData));

        assertTrue(exception.getMessage().contains("veiculo em estado inválido"));
    }

    @Test
    @DisplayName("Deve lancar excecao quando updatedAt for anterior ao createdAt no rebuild")
    void shouldThrowExceptionWhenUpdatedAtIsBeforeCreatedAt() {
        UUID existingId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = createdAt.minusSeconds(1);
        VehicleData vehicleData = VehicleDataBuilder.create().build();

        BusinessException exception = assertThrowsExactly(
            BusinessException.class,
            () -> Vehicle.rebuild(existingId, createdAt, updatedAt, VehicleStatus.ACTIVE, vehicleData)
        );

        assertTrue(exception.getMessage().contains("veiculo em estado inválido"));
    }

    @Test
    @DisplayName("Deve atualizar campos e updatedAt")
    void shouldUpdateVehicleFields() {
        Vehicle vehicle = Vehicle.create(VehicleDataBuilder.create().build());
        LocalDateTime oldUpdatedAt = vehicle.getUpdatedAt();

        VehicleData newData = VehicleDataBuilder.create()
            .withChassis("99945678901234567")
            .withPlate("XYZ1234")
            .withBrand("Tesla")
            .withModel("Model 3")
            .withYear(LocalDateTime.now().getYear() + 1)
            .withColor("Branco")
            .withPrice(new BigDecimal("50000.00"))
            .withType(VehicleType.CAR)
            .build();

        vehicle.update(newData);

        assertEquals("99945678901234567", vehicle.getChassis());
        assertEquals("XYZ1234", vehicle.getPlate());
        assertEquals("Tesla", vehicle.getBrand());
        assertEquals("Model 3", vehicle.getModel());
        assertEquals(LocalDateTime.now().getYear() + 1, vehicle.getYear());
        assertEquals("Branco", vehicle.getColor());
        assertEquals(0, new BigDecimal("50000.00").compareTo(vehicle.getPrice()));
        assertEquals(VehicleType.CAR, vehicle.getType());
        assertTrue(!vehicle.getUpdatedAt().isBefore(oldUpdatedAt));
    }

    @Test
    @DisplayName("Deve realizar soft delete alterando status para DELETED")
    void shouldSoftDeleteVehicle() {
        Vehicle vehicle = Vehicle.create(VehicleDataBuilder.create().build());
        LocalDateTime oldUpdatedAt = vehicle.getUpdatedAt();

        vehicle.delete();

        assertEquals(VehicleStatus.DELETED, vehicle.getStatus());
        assertTrue(!vehicle.getUpdatedAt().isBefore(oldUpdatedAt));
    }

    @Test
    @DisplayName("Nao deve lancar excecao para faixa de preco valida")
    void shouldNotThrowExceptionForValidPriceRange() {
        assertDoesNotThrow(() -> Vehicle.validatePriceRange(new BigDecimal("10.00"), new BigDecimal("10.00")));
        assertDoesNotThrow(() -> Vehicle.validatePriceRange(new BigDecimal("10.00"), new BigDecimal("20.00")));
        assertDoesNotThrow(() -> Vehicle.validatePriceRange(null, new BigDecimal("20.00")));
        assertDoesNotThrow(() -> Vehicle.validatePriceRange(new BigDecimal("10.00"), null));
    }

    @Test
    @DisplayName("Deve lancar excecao para faixa de preco invalida quando min maior que max")
    void shouldThrowExceptionForInvalidPriceRange() {
        BusinessException exception = assertThrowsExactly(
            BusinessException.class,
            () -> Vehicle.validatePriceRange(new BigDecimal("21.00"), new BigDecimal("20.00"))
        );

        assertEquals("Faixa de preço inválida", exception.getMessage());
        assertTrue(exception.getDetails().contains("O valor de minPreco não pode ser maior que maxPreco"));
    }
}

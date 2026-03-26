package tinnova.test.com.example.demo.domain.entities.vehicle;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.util.UUID;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import java.time.LocalDateTime;
import tinnova.test.com.example.demo.domain.entities.validators.VehicleValidator;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleData;
import br.com.fluentvalidator.context.ValidationResult;
import java.util.List;
import tinnova.test.com.example.demo.domain.exceptions.BusinessException;

@Getter
@SuperBuilder
public class Vehicle {

	private UUID id;
	private String chassis;
	private String plate;
	private String brand;
	private String model;
	private Integer year;
	private String color;
	private BigDecimal price;
	private VehicleType type;
	private VehicleStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private static final VehicleValidator VALIDATOR = new VehicleValidator();

	public static Vehicle create(VehicleData vehicleData) {
		Vehicle vehicle = Vehicle.builder()
			.id(UUID.randomUUID())
			.chassis(vehicleData.getChassis())
			.plate(vehicleData.getPlate())
			.brand(vehicleData.getBrand())
			.model(vehicleData.getModel())
			.year(vehicleData.getYear())
			.color(vehicleData.getColor())
			.price(vehicleData.getPrice())
			.type(vehicleData.getType())
			.status(VehicleStatus.ACTIVE)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();

		selfValidate(vehicle);
		return vehicle;
		
	}

	public static Vehicle rebuild(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, VehicleData vehicleData) {
		Vehicle vehicle = Vehicle.builder()
			.id(id)
			.chassis(vehicleData.getChassis())
			.plate(vehicleData.getPlate())
			.brand(vehicleData.getBrand())
			.model(vehicleData.getModel())
			.year(vehicleData.getYear())
			.color(vehicleData.getColor())
			.price(vehicleData.getPrice())
			.type(vehicleData.getType())
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();	

		selfValidate(vehicle);
		return vehicle;
	}

	public void update(VehicleData vehicleData) {
		this.chassis = vehicleData.getChassis();
		this.plate = vehicleData.getPlate();
		this.brand = vehicleData.getBrand();
		this.model = vehicleData.getModel();
		this.year = vehicleData.getYear();
		this.color = vehicleData.getColor();
		this.price = vehicleData.getPrice();
		this.type = vehicleData.getType();
		this.markUpdated();
	}

	public void delete() {
		this.status = VehicleStatus.DELETED;
		this.markUpdated();
	}

	private static void selfValidate(Vehicle vehicle) {
        ValidationResult result = VALIDATOR.validate(vehicle);
        if (!result.isValid()) {
            throw new BusinessException(
                    "veiculo em estado inválido: " + vehicle.getChassis(), List.of("veiculo em estado inválido"));
        }
    }

	private void markUpdated() {
        this.updatedAt = LocalDateTime.now();
    }

	public static void validatePriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
		if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
			throw new BusinessException(
				"Faixa de preço inválida",
				List.of("O valor de minPreco não pode ser maior que maxPreco"));
		}
	}
}

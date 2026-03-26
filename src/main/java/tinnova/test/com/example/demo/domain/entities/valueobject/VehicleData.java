package tinnova.test.com.example.demo.domain.entities.valueobject;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

@Data
@Builder
public class VehicleData {
	private String chassis;
	private String plate;
	private String brand;
	private String model;
	private Integer year;
	private String color;
	private BigDecimal price;
	private VehicleType type;
}

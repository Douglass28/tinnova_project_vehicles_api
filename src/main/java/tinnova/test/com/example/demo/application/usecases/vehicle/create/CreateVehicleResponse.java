package tinnova.test.com.example.demo.application.usecases.vehicle.create;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleResponse {

	@Schema(description = "ID do veículo", example = "123e4567-e89b-12d3-a456-426614174000")
	private UUID id;

	@Schema(description = "Chassi do veículo", example = "12345678901234567")
	private String chassis;

	@Schema(description = "Placa do veículo", example = "ABC1234")
	private String plate;

	@Schema(description = "Marca do veículo", example = "Ford")
	private String brand;

	@Schema(description = "Modelo do veículo", example = "Focus")
	private String model;

	@Schema(description = "Ano do veículo", example = "2020")
	private Integer year;

	@Schema(description = "Cor do veículo", example = "Azul")
	private String color;

	@Schema(description = "Preco do veiculo persistido em USD", example = "1900.00")
	private BigDecimal price;

	@Schema(description = "Tipo do veículo", example = "CAR")
	private VehicleType type;

}

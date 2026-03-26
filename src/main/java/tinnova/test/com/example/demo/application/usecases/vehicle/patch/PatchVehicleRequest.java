package tinnova.test.com.example.demo.application.usecases.vehicle.patch;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchVehicleRequest {

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

    @Schema(description = "Preço do veículo", example = "10000.00")
    private BigDecimal price;

    @Schema(description = "Tipo do veículo", example = "CAR")
    private VehicleType type;

    public PatchVehicleInput toInput(UUID id) {
        return PatchVehicleInput.builder()
            .id(id)
            .chassis(chassis)
            .plate(plate)
            .brand(brand)
            .model(model)
            .year(year)
            .color(color)
            .price(price)
            .type(type)
            .build();
    }
}

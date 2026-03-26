package tinnova.test.com.example.demo.application.usecases.vehicle.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class UpdateVehicleRequest {

    @NotBlank
    @Size(max = 17)
    @Schema(description = "Chassi do veículo", example = "12345678901234567")
    private String chassis;

    @NotBlank
    @Size(max = 10)
    @Schema(description = "Placa do veículo", example = "ABC1234")
    private String plate;

    @NotBlank
    @Size(max = 120)
    @Schema(description = "Marca do veículo", example = "Ford")
    private String brand;

    @NotBlank
    @Size(max = 120)
    @Schema(description = "Modelo do veículo", example = "Focus")
    private String model;

    @NotNull
    @Positive
    @Schema(description = "Ano do veículo", example = "2020")
    private Integer year;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "Cor do veículo", example = "Azul")
    private String color;

    @NotNull
    @Positive
    @Schema(description = "Preço do veículo", example = "10000.00")
    private BigDecimal price;

    @NotNull
    @Schema(description = "Tipo do veículo", example = "CAR")
    private VehicleType type;

    public UpdateVehicleInput toInput(UUID id) {
        return UpdateVehicleInput.builder()
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

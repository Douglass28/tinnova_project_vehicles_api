package tinnova.test.com.example.demo.application.persistence.vehicles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus;
import tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {

	@Id
	private UUID id;

	@NotBlank
	@Size(max = 17)
	@Column(unique = true, length = 17)
	private String chassis;

	@NotBlank
	@Size(max = 10)
	@Column(nullable = false, unique = true, length = 8)
	private String plate;

	@NotBlank
	@Size(max = 120)
	@Column(nullable = false, length = 120)
	private String brand;

	@NotBlank
	@Size(max = 120)
	@Column(nullable = false, length = 120)
	private String model;

	@NotNull
	@Positive
	@Column(nullable = false)
	private Integer year;

	@NotBlank
	@Size(max = 64)
	@Column(nullable = false, length = 64)
	private String color;

	@NotNull
	@Positive
	@Column(nullable = false, precision = 19, scale = 2)
	private BigDecimal price;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 32)
	private VehicleType type;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 32)
	private VehicleStatus status = VehicleStatus.ACTIVE;

	@CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;
}

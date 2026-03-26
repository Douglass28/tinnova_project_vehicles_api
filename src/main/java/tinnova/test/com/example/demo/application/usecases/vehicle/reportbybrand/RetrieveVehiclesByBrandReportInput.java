package tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
public class RetrieveVehiclesByBrandReportInput {

    private Pageable pageable;
}

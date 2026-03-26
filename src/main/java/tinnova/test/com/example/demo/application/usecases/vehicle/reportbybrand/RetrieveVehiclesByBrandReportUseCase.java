package tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand;

import java.util.List;
import org.springframework.data.domain.Page;
import tinnova.test.com.example.demo.application.usecases.UseCase;

public interface RetrieveVehiclesByBrandReportUseCase
    extends UseCase<RetrieveVehiclesByBrandReportInput, Page<RetrieveVehiclesByBrandReportResponse>> {
}

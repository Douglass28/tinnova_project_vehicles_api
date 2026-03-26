package tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand;

import java.util.List;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleBrandCount;

public class OutputMapper {

    private OutputMapper() {}

    public static RetrieveVehiclesByBrandReportResponse toResponse(VehicleBrandCount report) {
        return RetrieveVehiclesByBrandReportResponse.builder()
            .brand(report.brand())
            .count(report.count())
            .build();
    }

    public static List<RetrieveVehiclesByBrandReportResponse> toResponseList(List<VehicleBrandCount> reports) {
        return reports.stream().map(OutputMapper::toResponse).toList();
    }
}

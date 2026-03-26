package tinnova.test.com.example.demo.application.usecases.vehicle.reportbybrand;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import tinnova.test.com.example.demo.domain.entities.valueobject.VehicleBrandCount;
import tinnova.test.com.example.demo.domain.repository.DomainVehicleRepository;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class RetrieveVehiclesByBrandReport implements RetrieveVehiclesByBrandReportUseCase {

    private final DomainVehicleRepository vehicleRepository;

    @Override
    public Page<RetrieveVehiclesByBrandReportResponse> execute(RetrieveVehiclesByBrandReportInput input) {
        Page<VehicleBrandCount> report = vehicleRepository.countActiveVehiclesByBrand(input.getPageable());
        log.info("Relatório por marca gerado com sucesso: {} linhas", report.getNumberOfElements());
        return OutputMapper.toResponsePage(report);
    }
}

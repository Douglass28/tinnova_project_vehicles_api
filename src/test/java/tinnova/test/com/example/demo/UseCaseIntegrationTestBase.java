package tinnova.test.com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import tinnova.test.com.example.demo.application.persistence.vehicles.VehicleRepository;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
public abstract class UseCaseIntegrationTestBase {

    @Autowired
    protected VehicleRepository vehicleRepository;

    @BeforeEach
    void cleanDatabase() {
        vehicleRepository.deleteAll();
    }
}

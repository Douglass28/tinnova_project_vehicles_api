package tinnova.test.com.example.demo.application.persistence.vehicles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID>, JpaSpecificationExecutor<VehicleEntity> {

    @Query(value = """
        SELECT v.brand AS brand, COUNT(v.id) AS count
        FROM VehicleEntity v
        WHERE v.status = tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus.ACTIVE
        GROUP BY v.brand
        """,
        countQuery = """
        SELECT COUNT(DISTINCT v.brand)
        FROM VehicleEntity v
        WHERE v.status = tinnova.test.com.example.demo.application.persistence.vehicles.enums.VehicleStatus.ACTIVE
        """
    )
    Page<VehicleBrandCountProjection> countActiveVehiclesByBrand(Pageable pageable);

}

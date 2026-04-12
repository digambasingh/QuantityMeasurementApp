package bridgeLabz.quantity_measurement.repository;

import bridgeLabz.quantity_measurement.entity.QuantityMeasurementEntity;
import bridgeLabz.quantity_measurement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperation(String operation);

    List<QuantityMeasurementEntity> findByUserOrderByCreatedAtDesc(UserEntity user);

    List<QuantityMeasurementEntity> findAllByOrderByCreatedAtDesc();
}

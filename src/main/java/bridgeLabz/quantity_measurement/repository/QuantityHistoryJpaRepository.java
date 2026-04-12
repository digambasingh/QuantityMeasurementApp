package bridgeLabz.quantity_measurement.repository;

import bridgeLabz.quantity_measurement.entity.QuantityMeasurementEntity;
import bridgeLabz.quantity_measurement.entity.QuantityMeasurementHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuantityHistoryJpaRepository extends JpaRepository<QuantityMeasurementHistory, Long> {

    Optional<QuantityMeasurementHistory> findByEntity(QuantityMeasurementEntity entity);

    @Modifying
    @Query("UPDATE QuantityMeasurementHistory h SET h.operationCount = h.operationCount + 1 WHERE h.entity = :entity")
    void incrementOperationCount(@Param("entity") QuantityMeasurementEntity entity);
}

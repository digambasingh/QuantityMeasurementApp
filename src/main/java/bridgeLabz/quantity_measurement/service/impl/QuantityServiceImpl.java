package bridgeLabz.quantity_measurement.service.impl;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.entity.QuantityMeasurementEntity;
import bridgeLabz.quantity_measurement.entity.QuantityMeasurementHistory;
import bridgeLabz.quantity_measurement.entity.UserEntity;
import bridgeLabz.quantity_measurement.exception.CategoryMismatchException;
import bridgeLabz.quantity_measurement.factory.StrategyFactory;
import bridgeLabz.quantity_measurement.repository.QuantityHistoryJpaRepository;
import bridgeLabz.quantity_measurement.repository.QuantityMeasurementRepository;
import bridgeLabz.quantity_measurement.service.QuantityService;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuantityServiceImpl implements QuantityService {

    private final StrategyFactory factory;
    private final QuantityMeasurementRepository measurementRepo;
    private final QuantityHistoryJpaRepository historyRepo;

    // ─── Arithmetic Operations ───────────────────────────────────────

    @Override
    @Transactional
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, UserEntity user) {
        return executeOperation(q1, q2, "ADD", user);
    }

    @Override
    @Transactional
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, UserEntity user) {
        return executeOperation(q1, q2, "SUBTRACT", user);
    }

    @Override
    @Transactional
    public QuantityDTO multiply(QuantityDTO q1, QuantityDTO q2, UserEntity user) {
        return executeOperation(q1, q2, "MULTIPLY", user);
    }

    @Override
    @Transactional
    public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, UserEntity user) {
        return executeOperation(q1, q2, "DIVIDE", user);
    }

    // ─── Compare ─────────────────────────────────────────────────────

    @Override
    @Transactional
    public boolean compare(QuantityDTO q1, QuantityDTO q2, UserEntity user) {

        validateInputs(q1, q2);

        ConversionStrategy strategy = factory.getStrategy(q1.getMeasurementType());
        boolean result = strategy.compare(q1, q2);

        // Only save to DB if user is logged in
        if (user != null) {
            QuantityMeasurementEntity entity = buildEntity(q1, q2, "COMPARE");
            entity.setUser(user);
            try {
                entity.setResultValue(result ? 1.0 : 0.0);
                entity.setResultUnit("BOOLEAN");
                entity.setResultMeasurementType(q1.getMeasurementType());
                saveWithHistory(entity);
            } catch (Exception e) {
                saveErrorEntity(entity, e);
            }
        }

        return result;
    }

    // ─── Convert ─────────────────────────────────────────────────────

    @Override
    @Transactional
    public QuantityDTO convert(QuantityDTO q, String targetUnit, UserEntity user) {

        ConversionStrategy strategy = factory.getStrategy(q.getMeasurementType());

        double base = strategy.toBase(q.getValue(), q.getUnitName());
        double result = strategy.fromBase(base, targetUnit);

        QuantityDTO dto = new QuantityDTO(result, targetUnit, q.getMeasurementType());

        // Only save to DB if user is logged in
        if (user != null) {
            QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
            entity.setThisValue(q.getValue());
            entity.setThisUnit(q.getUnitName());
            entity.setThisMeasurementType(q.getMeasurementType());
            entity.setOperation("CONVERT");
            entity.setUser(user);
            entity.setResultValue(result);
            entity.setResultUnit(targetUnit);
            entity.setResultMeasurementType(q.getMeasurementType());
            saveWithHistory(entity);
        }

        return dto;
    }

    // ─── History (user-specific) ─────────────────────────────────────

    @Override
    public List<QuantityMeasurementEntity> getByUser(UserEntity user) {
        return measurementRepo.findByUserOrderByCreatedAtDesc(user);
    }

    // ─── Private Helpers ─────────────────────────────────────────────

    private QuantityDTO executeOperation(QuantityDTO q1, QuantityDTO q2,
                                         String operation, UserEntity user) {

        validateInputs(q1, q2);

        ConversionStrategy strategy = factory.getStrategy(q1.getMeasurementType());

        QuantityDTO result = switch (operation) {
            case "ADD" -> strategy.add(q1, q2);
            case "SUBTRACT" -> strategy.subtract(q1, q2);
            case "MULTIPLY" -> strategy.multiply(q1, q2);
            case "DIVIDE" -> strategy.divide(q1, q2);
            default -> throw new IllegalArgumentException("Unknown operation: " + operation);
        };

        // Only save to DB if user is logged in
        if (user != null) {
            QuantityMeasurementEntity entity = buildEntity(q1, q2, operation);
            entity.setUser(user);
            try {
                entity.setResultValue(result.getValue());
                entity.setResultUnit(result.getUnitName());
                entity.setResultMeasurementType(result.getMeasurementType());
                saveWithHistory(entity);
            } catch (Exception e) {
                saveErrorEntity(entity, e);
            }
        }

        return result;
    }

    private void validateInputs(QuantityDTO q1, QuantityDTO q2) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Input quantities cannot be null");
        }
        if (!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())) {
            throw new CategoryMismatchException(
                    "Measurement types must match: " + q1.getMeasurementType()
                            + " vs " + q2.getMeasurementType());
        }
    }

    private QuantityMeasurementEntity buildEntity(QuantityDTO q1, QuantityDTO q2, String operation) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnitName());
        entity.setThisMeasurementType(q1.getMeasurementType());

        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnitName());
        entity.setThatMeasurementType(q2.getMeasurementType());

        entity.setOperation(operation);
        return entity;
    }

    private void saveWithHistory(QuantityMeasurementEntity entity) {
        QuantityMeasurementEntity saved = measurementRepo.save(entity);

        QuantityMeasurementHistory history = new QuantityMeasurementHistory();
        history.setEntity(saved);
        history.setOperationCount(1);
        historyRepo.save(history);
    }

    private void saveErrorEntity(QuantityMeasurementEntity entity, Exception e) {
        entity.setError(true);
        entity.setErrorMessage(e.getMessage());
        QuantityMeasurementEntity saved = measurementRepo.save(entity);

        QuantityMeasurementHistory history = new QuantityMeasurementHistory();
        history.setEntity(saved);
        history.setOperationCount(1);
        historyRepo.save(history);
    }
}
package bridgeLabz.quantity_measurement.service.impl;
import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.entity.QuantityMeasurementEntity;
import bridgeLabz.quantity_measurement.factory.StrategyFactory;
import bridgeLabz.quantity_measurement.repository.QuantityHistoryRepository;
import bridgeLabz.quantity_measurement.repository.QuantityJdbcRepository;
import bridgeLabz.quantity_measurement.service.QuantityService;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuantityServiceImpl implements QuantityService {

//    @Autowired
    private final StrategyFactory factory;
    private final QuantityJdbcRepository repo;
    private final QuantityHistoryRepository historyRepo;

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        if (q1 == null || q2 == null) {
            throw new RuntimeException("Input cannot be null");
        }

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new RuntimeException("Measurement types must match");
        }

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnitName());
        entity.setThisMeasurementType(q1.getMeasurementType());
        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnitName());
        entity.setThatMeasurementType(q2.getMeasurementType());
        entity.setOperation("ADD");

        try {
            ConversionStrategy strategy = factory.getStrategy(q1.getMeasurementType());

            QuantityDTO result = strategy.add(q1, q2);

            entity.setResultValue(result.getValue());
            entity.setResultUnit(result.getUnitName());
            entity.setResultMeasurementType(result.getMeasurementType());

            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);
            return result;

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);
            throw e;
        }
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        if (q1 == null || q2 == null) {
            throw new RuntimeException("Input cannot be null");
        }

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new RuntimeException("Measurement types must match");
        }

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnitName());
        entity.setThisMeasurementType(q1.getMeasurementType());
        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnitName());
        entity.setThatMeasurementType(q2.getMeasurementType());
        entity.setOperation("SUBTRACT");

        try {
            ConversionStrategy strategy = factory.getStrategy(q1.getMeasurementType());

            QuantityDTO result = strategy.subtract(q1, q2);

            entity.setResultValue(result.getValue());
            entity.setResultUnit(result.getUnitName());
            entity.setResultMeasurementType(result.getMeasurementType());

            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);

            return result;

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());

            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);

            throw e;
        }
    }

    @Override
    public QuantityDTO multiply(QuantityDTO q1, QuantityDTO q2) {

        if (q1 == null || q2 == null) {
            throw new RuntimeException("Input cannot be null");
        }

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new RuntimeException("Measurement types must match");
        }

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnitName());
        entity.setThisMeasurementType(q1.getMeasurementType());
        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnitName());
        entity.setThatMeasurementType(q2.getMeasurementType());
        entity.setOperation("MULTIPLY");

        try {
            ConversionStrategy strategy = factory.getStrategy(q1.getMeasurementType());

            QuantityDTO result = strategy.multiply(q1, q2);

            entity.setResultValue(result.getValue());
            entity.setResultUnit(result.getUnitName());
            entity.setResultMeasurementType(result.getMeasurementType());

            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);

            return result;

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());

            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);

            throw e;
        }
    }

    @Override
    public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2) {

        if (q1 == null || q2 == null) {
            throw new RuntimeException("Input cannot be null");
        }

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new RuntimeException("Measurement types must match");
        }

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnitName());
        entity.setThisMeasurementType(q1.getMeasurementType());
        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnitName());
        entity.setThatMeasurementType(q2.getMeasurementType());
        entity.setOperation("DIVIDE");

        try {
            ConversionStrategy strategy = factory.getStrategy(q1.getMeasurementType());

            QuantityDTO result = strategy.divide(q1, q2);

            entity.setResultValue(result.getValue());
            entity.setResultUnit(result.getUnitName());
            entity.setResultMeasurementType(result.getMeasurementType());

            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);

            return result;

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());

            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);

            throw e;
        }
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnitName());
        entity.setThisMeasurementType(q1.getMeasurementType());

        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnitName());
        entity.setThatMeasurementType(q2.getMeasurementType());

        entity.setOperation("COMPARE");

        try {
            if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
                throw new RuntimeException("Category mismatch");
            }

            ConversionStrategy strategy = factory.getStrategy(q1.getMeasurementType());

            boolean result = strategy.compare(q1, q2);

            entity.setResultValue(result ? 1.0 : 0.0);
            entity.setResultUnit("BOOLEAN");
            entity.setResultMeasurementType(q1.getMeasurementType());

//            repo.save(entity);  // SAVE HERE
            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);
            return result;

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
//            repo.save(entity);
            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);
            throw e;
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(q.getValue());
        entity.setThisUnit(q.getUnitName());
        entity.setThisMeasurementType(q.getMeasurementType());
        entity.setOperation("CONVERT");

        try {
            ConversionStrategy strategy = factory.getStrategy(q.getMeasurementType());

            double base = strategy.toBase(q.getValue(), q.getUnitName());
            double result = strategy.fromBase(base, targetUnit);

            QuantityDTO dto = new QuantityDTO(result, targetUnit, q.getMeasurementType());

            entity.setResultValue(result);
            entity.setResultUnit(targetUnit);
            entity.setResultMeasurementType(q.getMeasurementType());

//            repo.save(entity);  //  SAVE HERE
            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);

            return dto;

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
//            repo.save(entity);
            Long id = repo.saveAndReturnId(entity);
            historyRepo.saveOrUpdate(id);
            throw e;
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAll() {
        return repo.findAll();
    }
}
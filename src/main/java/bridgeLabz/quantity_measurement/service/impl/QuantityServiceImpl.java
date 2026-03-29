package bridgeLabz.quantity_measurement.service.impl;
import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.factory.StrategyFactory;
import bridgeLabz.quantity_measurement.service.QuantityService;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl implements QuantityService {

    @Autowired
    private StrategyFactory factory;

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new RuntimeException("Category mismatch");
        }

        ConversionStrategy strategy = factory.getStrategy(q1.getMeasurementType());

        return strategy.add(q1, q2);
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        if (!q1.getMeasurementType().equals(q2.getMeasurementType())) {
            throw new RuntimeException("Category mismatch");
        }

        ConversionStrategy strategy = factory.getStrategy(q1.getMeasurementType());

        return strategy.compare(q1, q2);
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        if (q == null) {
            throw new RuntimeException("Input cannot be null");
        }

        ConversionStrategy strategy = factory.getStrategy(q.getMeasurementType());

        double base = strategy.toBase(q.getValue(), q.getUnitName());
        double result = strategy.fromBase(base, targetUnit);

        return new QuantityDTO(result, targetUnit, q.getMeasurementType());
    }
}
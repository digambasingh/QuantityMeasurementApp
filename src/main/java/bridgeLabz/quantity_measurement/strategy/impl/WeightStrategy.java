package bridgeLabz.quantity_measurement.strategy.impl;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("WeightUnit")
public class WeightStrategy implements ConversionStrategy {

    private static final Map<String, Double> factors = Map.of(
            "KG", 1.0,
            "GRAM", 0.001
    );

    @Override
    public double toBase(double value, String unit) {
        return value * factors.get(unit.toUpperCase());
    }

    @Override
    public double fromBase(double baseValue, String unit) {
        return baseValue / factors.get(unit.toUpperCase());
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        double base1 = toBase(q1.getValue(), q1.getUnitName());
        double base2 = toBase(q2.getValue(), q2.getUnitName());

        double result = base1 + base2;

        double finalValue = fromBase(result, q1.getUnitName());

        return new QuantityDTO(finalValue, q1.getUnitName(), "WeightUnit");
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        double base1 = toBase(q1.getValue(), q1.getUnitName());
        double base2 = toBase(q2.getValue(), q2.getUnitName());

        return Math.abs(base1 - base2) < 0.0001;
    }
}
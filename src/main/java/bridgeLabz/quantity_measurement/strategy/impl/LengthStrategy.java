package bridgeLabz.quantity_measurement.strategy.impl;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.exception.InvalidUnitException;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("LengthUnit")
public class LengthStrategy implements ConversionStrategy {

    private static final Map<String, Double> factors = Map.of(
            "FEET", 0.3048,
            "INCHES", 0.0254,
            "METER", 1.0,
            "CENTIMETER", 0.01,
            "KILOMETER", 1000.0,
            "YARD", 0.9144,
            "MILE", 1609.344
    );

    @Override
    public double toBase(double value, String unit) {
        Double factor = factors.get(unit.toUpperCase());
        if (factor == null) {
            throw new InvalidUnitException("Invalid length unit: " + unit
                    + ". Supported: " + factors.keySet());
        }
        return value * factor;
    }

    @Override
    public double fromBase(double baseValue, String unit) {
        Double factor = factors.get(unit.toUpperCase());
        if (factor == null) {
            throw new InvalidUnitException("Invalid length unit: " + unit
                    + ". Supported: " + factors.keySet());
        }
        return baseValue / factor;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        double base1 = toBase(q1.getValue(), q1.getUnitName());
        double base2 = toBase(q2.getValue(), q2.getUnitName());
        double finalValue = fromBase(base1 + base2, q1.getUnitName());
        return new QuantityDTO(finalValue, q1.getUnitName(), "LengthUnit");
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        double result = toBase(q1.getValue(), q1.getUnitName())
                - toBase(q2.getValue(), q2.getUnitName());
        return new QuantityDTO(fromBase(result, q1.getUnitName()),
                q1.getUnitName(), "LengthUnit");
    }

    @Override
    public QuantityDTO multiply(QuantityDTO q1, QuantityDTO q2) {
        double result = toBase(q1.getValue(), q1.getUnitName())
                * toBase(q2.getValue(), q2.getUnitName());
        return new QuantityDTO(result, "BASE", "LengthUnit");
    }

    @Override
    public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2) {
        double divisor = toBase(q2.getValue(), q2.getUnitName());
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
        }
        double result = toBase(q1.getValue(), q1.getUnitName()) / divisor;
        return new QuantityDTO(result, "BASE", "LengthUnit");
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        double base1 = toBase(q1.getValue(), q1.getUnitName());
        double base2 = toBase(q2.getValue(), q2.getUnitName());
        return Math.abs(base1 - base2) < 0.0001;
    }
}
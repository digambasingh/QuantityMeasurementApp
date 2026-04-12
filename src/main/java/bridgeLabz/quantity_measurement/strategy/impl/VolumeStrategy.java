package bridgeLabz.quantity_measurement.strategy.impl;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.exception.InvalidUnitException;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("VolumeUnit")
public class VolumeStrategy implements ConversionStrategy {

    private static final Map<String, Double> factors = Map.of(
            "LITER", 1.0,
            "ML", 0.001,
            "GALLON", 3.78541
    );

    @Override
    public double toBase(double value, String unit) {
        Double factor = factors.get(unit.toUpperCase());
        if (factor == null) {
            throw new InvalidUnitException("Invalid volume unit: " + unit
                    + ". Supported: " + factors.keySet());
        }
        return value * factor;
    }

    @Override
    public double fromBase(double baseValue, String unit) {
        Double factor = factors.get(unit.toUpperCase());
        if (factor == null) {
            throw new InvalidUnitException("Invalid volume unit: " + unit
                    + ". Supported: " + factors.keySet());
        }
        return baseValue / factor;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        double result = toBase(q1.getValue(), q1.getUnitName())
                + toBase(q2.getValue(), q2.getUnitName());
        return new QuantityDTO(fromBase(result, q1.getUnitName()),
                q1.getUnitName(), "VolumeUnit");
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        double result = toBase(q1.getValue(), q1.getUnitName())
                - toBase(q2.getValue(), q2.getUnitName());
        return new QuantityDTO(fromBase(result, q1.getUnitName()),
                q1.getUnitName(), "VolumeUnit");
    }

    @Override
    public QuantityDTO multiply(QuantityDTO q1, QuantityDTO q2) {
        double result = toBase(q1.getValue(), q1.getUnitName())
                * toBase(q2.getValue(), q2.getUnitName());
        return new QuantityDTO(result, "BASE", "VolumeUnit");
    }

    @Override
    public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2) {
        double divisor = toBase(q2.getValue(), q2.getUnitName());
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
        }
        double result = toBase(q1.getValue(), q1.getUnitName()) / divisor;
        return new QuantityDTO(result, "BASE", "VolumeUnit");
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return Math.abs(
                toBase(q1.getValue(), q1.getUnitName()) -
                        toBase(q2.getValue(), q2.getUnitName())
        ) < 0.0001;
    }
}
package bridgeLabz.quantity_measurement.strategy.impl;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.exception.InvalidUnitException;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;
import org.springframework.stereotype.Component;

@Component("TemperatureUnit")
public class TemperatureStrategy implements ConversionStrategy {

    @Override
    public double toBase(double value, String unit) {
        String u = unit.trim().toUpperCase();
        return switch (u) {
            case "FAHRENHEIT" -> (value - 32) * 5 / 9;
            case "CELSIUS" -> value;
            case "KELVIN" -> value - 273.15;
            default -> throw new InvalidUnitException(
                    "Invalid temperature unit: " + unit
                            + ". Supported: CELSIUS, FAHRENHEIT, KELVIN");
        };
    }

    @Override
    public double fromBase(double baseValue, String unit) {
        String u = unit.trim().toUpperCase();
        return switch (u) {
            case "FAHRENHEIT" -> (baseValue * 9 / 5) + 32;
            case "CELSIUS" -> baseValue;
            case "KELVIN" -> baseValue + 273.15;
            default -> throw new InvalidUnitException(
                    "Invalid temperature unit: " + unit
                            + ". Supported: CELSIUS, FAHRENHEIT, KELVIN");
        };
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        throw new bridgeLabz.quantity_measurement.exception.UnsupportedOperationException(
                "Addition not supported for temperature");
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        throw new bridgeLabz.quantity_measurement.exception.UnsupportedOperationException(
                "Subtraction not supported for temperature");
    }

    @Override
    public QuantityDTO multiply(QuantityDTO q1, QuantityDTO q2) {
        throw new bridgeLabz.quantity_measurement.exception.UnsupportedOperationException(
                "Multiplication not supported for temperature");
    }

    @Override
    public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2) {
        throw new bridgeLabz.quantity_measurement.exception.UnsupportedOperationException(
                "Division not supported for temperature");
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        double c1 = toBase(q1.getValue(), q1.getUnitName());
        double c2 = toBase(q2.getValue(), q2.getUnitName());
        return Math.abs(c1 - c2) < 0.0001;
    }
}
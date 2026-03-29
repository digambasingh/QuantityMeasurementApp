package bridgeLabz.quantity_measurement.strategy.impl;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;
import org.springframework.stereotype.Component;

@Component("TemperatureUnit")
public class TemperatureStrategy implements ConversionStrategy {

    @Override
    public double toBase(double value, String unit) {
        if (unit.equals("FAHRENHEIT")) {
            return (value - 32) * 5 / 9;
        }
        return value;
    }

    @Override
    public double fromBase(double baseValue, String unit) {
        if (unit.equals("FAHRENHEIT")) {
            return (baseValue * 9 / 5) + 32;
        }
        return baseValue;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        throw new RuntimeException("Addition not supported for temperature");
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        double c1 = toBase(q1.getValue(), q1.getUnitName());
        double c2 = toBase(q2.getValue(), q2.getUnitName());

        return Math.abs(c1 - c2) < 0.0001;
    }
}
package DigambarSoftwareSolutions.QuantityMeasurementApp;

import java.util.function.Function;

/**
 * Temperature measurement units.
 * Celsius is treated as the base unit.
 */
public enum TemperatureUnit implements IMeasurable {

    CELSIUS(false),
    FAHRENHEIT(true);

    private final boolean isFahrenheit;

    // Functional interface for conversion
    private Function<Double, Double> conversionFunction;

    // Temperature does NOT support arithmetic operations
    SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(boolean isFahrenheit) {
        this.isFahrenheit = isFahrenheit;

        if (isFahrenheit) {
            conversionFunction = f -> (f - 32) * 5 / 9;
        } else {
            conversionFunction = c -> c;
        }
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    /**
     * Convert value to base unit (Celsius)
     */
    @Override
    public double convertToBaseUnit(double value) {
        return conversionFunction.apply(value);
    }

    /**
     * Convert from base unit (Celsius) to this unit
     */
    @Override
    public double convertFromBaseUnit(double baseValue) {

        if (this == CELSIUS) {
            return baseValue;
        }

        if (this == FAHRENHEIT) {
            return (baseValue * 9 / 5) + 32;
        }

        throw new IllegalArgumentException("Unsupported temperature unit");
    }

    /**
     * Override arithmetic support
     */
    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    /**
     * Prevent arithmetic operations on temperature
     */
    @Override
    public void validateOperationSupport(String operation) {

        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException(
                    "Temperature does not support " + operation + " operation.");
        }
    }
}

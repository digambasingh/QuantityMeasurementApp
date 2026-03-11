package DigambarSoftwareSolutions.QuantityMeasurementApp;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException("Invalid input");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    /**
     * Convert quantity to another unit
     */
    public Quantity<U> convertTo(U targetUnit) {

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    /**
     * Equality check using base unit conversion
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity<?> other))
            return false;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.0001;
    }

    /**
     * Addition
     */
    public Quantity<U> add(Quantity<U> other) {

        unit.validateOperationSupport("addition");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 + base2;

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, unit);
    }

    /**
     * Subtraction
     */
    public Quantity<U> subtract(Quantity<U> other) {

        unit.validateOperationSupport("subtraction");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, unit);
    }

    /**
     * Division
     */
    public double divide(Quantity<U> other) {

        unit.validateOperationSupport("division");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (base2 == 0)
            throw new ArithmeticException("Division by zero");

        return base1 / base2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }
}
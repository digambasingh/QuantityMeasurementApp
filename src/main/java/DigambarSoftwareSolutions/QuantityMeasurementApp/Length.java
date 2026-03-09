package DigambarSoftwareSolutions.QuantityMeasurementApp;

import java.util.Objects;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException("Invalid input");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    // Convert to base unit (inches)
    private double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    // Equality comparison
    public boolean compare(Length other) {
        if (other == null) return false;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Length)) return false;

        Length other = (Length) obj;
        return compare(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    // UC5 Conversion method
    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit null");

        double base = this.toBaseUnit();
        double converted = base / targetUnit.getConversionFactor();

        return new Length(converted, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
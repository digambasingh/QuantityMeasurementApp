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
    
 // UC6: Addition of two Length objects
    public Length add(Length other) {

        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        // convert both values to base unit (inches)
        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        // add them
        double sum = base1 + base2;

        // convert result back to the unit of the first operand
        double resultValue = sum / this.unit.getConversionFactor();

        return new Length(resultValue, this.unit);
    }
    
 // UC7: Addition with explicit target unit
    public Length add(Length other, LengthUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Invalid numeric value");

        // convert both values to base unit (inches)
        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        // add them
        double sum = base1 + base2;

        // convert sum to target unit
        double resultValue = sum / targetUnit.getConversionFactor();

        return new Length(resultValue, targetUnit);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
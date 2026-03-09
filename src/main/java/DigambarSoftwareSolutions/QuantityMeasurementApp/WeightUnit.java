package DigambarSoftwareSolutions.QuantityMeasurementApp;

public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKilogramFactor;

    WeightUnit(double toKilogramFactor) {
        this.toKilogramFactor = toKilogramFactor;
    }

    public double getConversionFactor() {
        return toKilogramFactor;
    }

    // Convert THIS unit to base unit (kilogram)
    public double convertToBaseUnit(double value) {
        return value * toKilogramFactor;
    }

    // Convert from base unit (kilogram) to THIS unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toKilogramFactor;
    }
}
package DigambarSoftwareSolutions.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ---------- BASIC CONVERSION TESTS ----------

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.convert(1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityMeasurementApp.convert(24.0,
                LengthUnit.INCHES,
                LengthUnit.FEET);

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityMeasurementApp.convert(1.0,
                LengthUnit.YARDS,
                LengthUnit.INCHES);

        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = QuantityMeasurementApp.convert(72.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS);

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.convert(2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES);

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = QuantityMeasurementApp.convert(6.0,
                LengthUnit.FEET,
                LengthUnit.YARDS);

        assertEquals(2.0, result, EPSILON);
    }

    // ---------- ROUND TRIP TEST ----------

    @Test
    void testConversion_RoundTrip_PreservesValue() {

        double value = 5.0;

        double converted = QuantityMeasurementApp.convert(value,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        double result = QuantityMeasurementApp.convert(converted,
                LengthUnit.INCHES,
                LengthUnit.FEET);

        assertEquals(value, result, EPSILON);
    }

    // ---------- EDGE CASES ----------

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.convert(0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityMeasurementApp.convert(-1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(-12.0, result, EPSILON);
    }

    // ---------- EXCEPTION TESTS ----------

    @Test
    void testConversion_InvalidUnit_Throws() {

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(1.0,
                    null,
                    LengthUnit.INCHES);
        });
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(Double.NaN,
                    LengthUnit.FEET,
                    LengthUnit.INCHES);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY,
                    LengthUnit.FEET,
                    LengthUnit.INCHES);
        });
    }

    // ---------- PRECISION TEST ----------

    @Test
    void testConversion_PrecisionTolerance() {

        double result = QuantityMeasurementApp.convert(30.48,
                LengthUnit.CENTIMETERS,
                LengthUnit.FEET);

        assertEquals(1.0, result, EPSILON);
    }
}


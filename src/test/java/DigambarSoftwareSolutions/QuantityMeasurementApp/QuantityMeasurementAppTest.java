package DigambarSoftwareSolutions.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ---------- ENUM CONSTANT TESTS ----------

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
    }

    // ---------- CONVERT TO BASE UNIT ----------

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPSILON);
    }

    // ---------- CONVERT FROM BASE UNIT ----------

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), EPSILON);
    }

    // ---------- QUANTITY TESTS ----------

    @Test
    void testQuantityLengthRefactored_Equality() {

        Length q1 = new Length(1.0, LengthUnit.FEET);
        Length q2 = new Length(12.0, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {

        Length q1 = new Length(1.0, LengthUnit.FEET);
        Length result = q1.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testQuantityLengthRefactored_Add() {

        Length q1 = new Length(1.0, LengthUnit.FEET);
        Length q2 = new Length(12.0, LengthUnit.INCHES);

        Length result = q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {

        Length q1 = new Length(1.0, LengthUnit.FEET);
        Length q2 = new Length(12.0, LengthUnit.INCHES);

        Length result = q1.add(q2, LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // ---------- VALIDATION TESTS ----------

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.NaN, LengthUnit.FEET));
    }

    // ---------- ROUND TRIP CONVERSION ----------

    @Test
    void testRoundTripConversion_RefactoredDesign() {

        double value = 5.0;

        double converted = LengthUnit.INCHES.convertFromBaseUnit(
                LengthUnit.FEET.convertToBaseUnit(value)
        );

        double back = LengthUnit.FEET.convertFromBaseUnit(
                LengthUnit.INCHES.convertToBaseUnit(converted)
        );

        assertEquals(value, back, EPSILON);
    }

    // ---------- ENUM IMMUTABILITY ----------

    @Test
    void testUnitImmutability() {
        LengthUnit unit = LengthUnit.FEET;
        assertEquals("FEET", unit.name());
    }

}
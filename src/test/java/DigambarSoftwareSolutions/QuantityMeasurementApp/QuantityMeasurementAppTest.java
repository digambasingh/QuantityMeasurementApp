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
    
    
 // ---------- UC6 ADDITION TESTS ----------

    @Test
    void testAddition_FeetAndFeet() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_FeetAndInches() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_InchesAndFeet() {

        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_YardsAndFeet() {

        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_InchesAndYards() {

        Length l1 = new Length(36.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.YARDS);

        Length result = l1.add(l2);

        assertEquals(new Length(72.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_CentimetersAndInches() {

        Length l1 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(5.08, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_WithZero() {

        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_WithNegativeValue() {

        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }
    
 // ---------- UC7 ADDITION WITH TARGET UNIT TESTS ----------

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.INCHES);

        assertEquals(new Length(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);

        assertEquals(0.6666667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        Length l1 = new Length(2.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);

        assertEquals(new Length(3.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        Length l1 = new Length(2.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.FEET);

        assertEquals(new Length(9.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result1 = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);
        Length result2 = QuantityMeasurementApp.add(l2, l1, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);

        assertEquals(1.6666667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        Length l1 = new Length(1000.0, LengthUnit.FEET);
        Length l2 = new Length(500.0, LengthUnit.FEET);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = QuantityMeasurementApp.add(l1, l2, LengthUnit.YARDS);

        assertEquals(0.6666667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.add(l1, l2, null);
        });
    }
}
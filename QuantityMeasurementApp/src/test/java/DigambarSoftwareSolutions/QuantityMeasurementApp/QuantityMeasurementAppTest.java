package DigambarSoftwareSolutions.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

    // ---------- BASIC UC3 TESTS ----------

    @Test
    void testEquality_FeetToFeet_SameValue() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(2.0, LengthUnit.INCHES);
        assertFalse(l1.equals(l2));
    }

    // ---------- YARD TESTS ----------

    @Test
    void testEquality_YardToYard_SameValue() {
        assertTrue(new Length(1, LengthUnit.YARDS).equals(new Length(1, LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertFalse(new Length(1, LengthUnit.YARDS).equals(new Length(2, LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(new Length(1, LengthUnit.YARDS).equals(new Length(3, LengthUnit.FEET)));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertTrue(new Length(3, LengthUnit.FEET).equals(new Length(1, LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertTrue(new Length(1, LengthUnit.YARDS).equals(new Length(36, LengthUnit.INCHES)));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        assertTrue(new Length(36, LengthUnit.INCHES).equals(new Length(1, LengthUnit.YARDS)));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertFalse(new Length(1, LengthUnit.YARDS).equals(new Length(2, LengthUnit.FEET)));
    }

    // ---------- CENTIMETER TESTS ----------

    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        assertTrue(new Length(1, LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, LengthUnit.INCHES)));
    }

    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        assertFalse(new Length(1, LengthUnit.CENTIMETERS)
                .equals(new Length(1, LengthUnit.FEET)));
    }

    // ---------- TRANSITIVE PROPERTY ----------

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        Length yard = new Length(1, LengthUnit.YARDS);
        Length feet = new Length(3, LengthUnit.FEET);
        Length inches = new Length(36, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    // ---------- CONTRACT TESTS ----------

    @Test
    void testEquality_YardSameReference() {
        Length l = new Length(2, LengthUnit.YARDS);
        assertTrue(l.equals(l));
    }

    @Test
    void testEquality_YardNullComparison() {
        Length l = new Length(2, LengthUnit.YARDS);
        assertFalse(l.equals(null));
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        Length yard = new Length(2, LengthUnit.YARDS);
        Length feet = new Length(6, LengthUnit.FEET);
        Length inches = new Length(72, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }
}
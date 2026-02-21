package DigambarSoftwareSolutions.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

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

    @Test
    void testEquality_SameReference() {
        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l1));
    }

    @Test
    void testEquality_NullComparison() {
        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertFalse(l1.equals(null));
    }

    @Test
    void testEquality_InvalidClass() {
        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertFalse(l1.equals("abc"));
    }
}
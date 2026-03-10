package DigambarSoftwareSolutions.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

private static final double EPSILON = 1e-6;

// Subtraction - same unit
@Test
void testSubtraction_SameUnit_FeetMinusFeet() {
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

    Quantity<LengthUnit> result = q1.subtract(q2);

    assertEquals(5.0, result.getValue(), EPSILON);
}

@Test
void testSubtraction_SameUnit_LitreMinusLitre() {
    Quantity<VolumeUnit> q1 = new Quantity<>(10.0, VolumeUnit.LITRE);
    Quantity<VolumeUnit> q2 = new Quantity<>(3.0, VolumeUnit.LITRE);

    Quantity<VolumeUnit> result = q1.subtract(q2);

    assertEquals(7.0, result.getValue(), EPSILON);
}

// Subtraction - cross unit
@Test
void testSubtraction_CrossUnit_FeetMinusInches() {
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

    Quantity<LengthUnit> result = q1.subtract(q2);

    assertEquals(9.5, result.getValue(), EPSILON);
}

@Test
void testSubtraction_CrossUnit_InchesMinusFeet() {
    Quantity<LengthUnit> q1 = new Quantity<>(120.0, LengthUnit.INCHES);
    Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

    Quantity<LengthUnit> result = q1.subtract(q2);

    assertEquals(60.0, result.getValue(), EPSILON);
}

// Subtraction - explicit target unit
@Test
void testSubtraction_ExplicitTargetUnit_Inches() {
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

    Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);

    assertEquals(114.0, result.getValue(), EPSILON);
}

// Subtraction - negative & zero
@Test
void testSubtraction_ResultingInNegative() {
    Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<LengthUnit> result = q1.subtract(q2);

    assertEquals(-5.0, result.getValue(), EPSILON);
}

@Test
void testSubtraction_ResultingInZero() {
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(120.0, LengthUnit.INCHES);

    Quantity<LengthUnit> result = q1.subtract(q2);

    assertEquals(0.0, result.getValue(), EPSILON);
}

// division test
@Test
void testDivision_SameUnit_FeetDividedByFeet() {
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

    double result = q1.divide(q2);

    assertEquals(5.0, result, EPSILON);
}

@Test
void testDivision_CrossUnit_FeetDividedByInches() {
    Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
    Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

    double result = q1.divide(q2);

    assertEquals(1.0, result, EPSILON);
}

// division - error handling
@Test
void testDivision_ByZero() {
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

    assertThrows(ArithmeticException.class, () -> q1.divide(q2));
}

@Test
void testDivision_NullOperand() {
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);

    assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
}

// Immutability Test
@Test
void testSubtraction_Immutability() {
    Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

    q1.subtract(q2);

    assertEquals(10.0, q1.getValue(), EPSILON);
}

// Mathematical Test
@Test
void testSubtraction_NonCommutative() {

    Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

    Quantity<LengthUnit> r1 = a.subtract(b);
    Quantity<LengthUnit> r2 = b.subtract(a);

    assertNotEquals(r1.getValue(), r2.getValue());
}

// Integration Test
@Test
void testSubtractionAndDivision_Integration() {

    Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
    Quantity<LengthUnit> c = new Quantity<>(4.0, LengthUnit.FEET);

    double result = a.subtract(b).divide(c);

    assertEquals(2.0, result, EPSILON);
}


}
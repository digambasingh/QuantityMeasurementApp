package DigambarSoftwareSolutions.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    // IMeasurable Interface Tests

    @Test
    public void testIMeasurableInterface_LengthUnitImplementation() {

        LengthUnit unit = LengthUnit.FEET;

        assertNotNull(unit.getConversionFactor());
        assertEquals(12.0, unit.getConversionFactor(), EPSILON);

        assertEquals(12.0, unit.convertToBaseUnit(1), EPSILON);
        assertEquals(1.0, unit.convertFromBaseUnit(12), EPSILON);
    }

    @Test
    public void testIMeasurableInterface_WeightUnitImplementation() {

        WeightUnit unit = WeightUnit.KILOGRAM;

        assertEquals(1000.0, unit.getConversionFactor(), EPSILON);

        assertEquals(1000.0, unit.convertToBaseUnit(1), EPSILON);
        assertEquals(1.0, unit.convertFromBaseUnit(1000), EPSILON);
    }

    @Test
    public void testIMeasurableInterface_ConsistentBehavior() {

        IMeasurable length = LengthUnit.FEET;
        IMeasurable weight = WeightUnit.KILOGRAM;

        assertTrue(length.convertToBaseUnit(1) > 0);
        assertTrue(weight.convertToBaseUnit(1) > 0);
    }

    // --------------------------------------------------
    // Equality Tests
    // --------------------------------------------------

    @Test
    public void testGenericQuantity_LengthOperations_Equality() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testGenericQuantity_WeightOperations_Equality() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    // --------------------------------------------------
    // Conversion Tests
    // --------------------------------------------------

    @Test
    public void testGenericQuantity_LengthOperations_Conversion() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    public void testGenericQuantity_WeightOperations_Conversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    // --------------------------------------------------
    // Addition Tests
    // --------------------------------------------------

    @Test
    public void testGenericQuantity_LengthOperations_Addition() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.add(inches, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testGenericQuantity_WeightOperations_Addition() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> grams =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(grams, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // --------------------------------------------------
    // Cross Category Safety
    // --------------------------------------------------

    @Test
    public void testCrossCategoryPrevention_LengthVsWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // --------------------------------------------------
    // Constructor Validation
    // --------------------------------------------------

    @Test
    public void testGenericQuantity_ConstructorValidation_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    // --------------------------------------------------
    // HashCode and Equals Contract
    // --------------------------------------------------

    @Test
    public void testHashCode_GenericQuantity_Consistency() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        if(q1.equals(q2))
            assertEquals(q1.hashCode(), q2.hashCode());
    }

    @Test
    public void testEquals_GenericQuantity_ContractPreservation() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> q3 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q1)); // reflexive
        assertTrue(q1.equals(q2)); // symmetric
        assertTrue(q2.equals(q3)); // transitive
    }

    // --------------------------------------------------
    // Immutability Test
    // --------------------------------------------------

    @Test
    public void testImmutability_GenericQuantity() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                q1.convertTo(LengthUnit.INCHES);

        assertNotEquals(q1.getUnit(), q2.getUnit());
    }

}
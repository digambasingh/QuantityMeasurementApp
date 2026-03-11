package DigambarSoftwareSolutions.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureQuantityTest {

    private static final double EPSILON = 0.0001;

    // ---------- Equality Tests ----------

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        assertTrue(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS))
        );
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        assertTrue(
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
                        .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT))
        );
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        assertTrue(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT))
        );
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        assertTrue(
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT))
        );
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
        assertTrue(
                new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT))
        );
    }

    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> temp =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        assertTrue(temp.equals(temp));
    }

    @Test
    void testTemperatureEquality_SymmetricProperty() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(c.equals(f));
        assertTrue(f.equals(c));
    }

    // ---------- Conversion Tests ----------

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                c.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(122.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius() {

        Quantity<TemperatureUnit> f =
                new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> result =
                f.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(50.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_RoundTrip_PreservesValue() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(70.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                c.convertTo(TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> back =
                f.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(70.0, back.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_SameUnit() {

        Quantity<TemperatureUnit> c =
                new Quantity<>(10.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                c.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(10.0, result.getValue(), EPSILON);
    }

    // ---------- Unsupported Operation Tests ----------

    @Test
    void testTemperatureUnsupportedOperation_Add() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.add(t2)
        );
    }

    @Test
    void testTemperatureUnsupportedOperation_Subtract() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.subtract(t2)
        );
    }

    @Test
    void testTemperatureUnsupportedOperation_Divide() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertThrows(
                UnsupportedOperationException.class,
                () -> t1.divide(t2)
        );
    }

    // ---------- Validation Tests ----------

    @Test
    void testTemperatureNullUnitValidation() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(100.0, null)
        );
    }

    @Test
    void testTemperatureNullOperandValidation_InComparison() {

        Quantity<TemperatureUnit> temp =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertFalse(temp.equals(null));
    }

    @Test
    void testTemperatureDifferentValuesInequality() {

        Quantity<TemperatureUnit> t1 =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> t2 =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertFalse(t1.equals(t2));
    }

    // ---------- Enum Tests ----------

    @Test
    void testTemperatureUnit_AllConstants() {

        assertNotNull(TemperatureUnit.CELSIUS);
        assertNotNull(TemperatureUnit.FAHRENHEIT);
    }

    @Test
    void testTemperatureUnit_NameMethod() {

        assertEquals("CELSIUS",
                TemperatureUnit.CELSIUS.getUnitName());
    }

    @Test
    void testTemperatureUnit_ConversionFactor() {

        assertEquals(1.0,
                TemperatureUnit.CELSIUS.getConversionFactor());
    }

    // ---------- Operation Validation ----------

    @Test
    void testTemperatureValidateOperationSupport_MethodBehavior() {

        assertThrows(
                UnsupportedOperationException.class,
                () -> TemperatureUnit.CELSIUS
                        .validateOperationSupport("addition")
        );
    }

    // ---------- Interface Implementation ----------

    @Test
    void testTemperatureEnumImplementsIMeasurable() {

        assertTrue(
                TemperatureUnit.CELSIUS instanceof IMeasurable
        );
    }
}
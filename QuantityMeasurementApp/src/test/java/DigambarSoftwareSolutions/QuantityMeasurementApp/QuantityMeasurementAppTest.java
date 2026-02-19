package DigambarSoftwareSolutions.QuantityMeasurementApp;

	import static org.junit.jupiter.api.Assertions.*;
	import org.junit.jupiter.api.Test;

	public class QuantityMeasurementAppTest {

	    @Test
	    void testEquality_SameValue() {
	    	QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
	    	QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);

	        assertTrue(f1.equals(f2), "1.0 ft should equal 1.0 ft");
	    }

	    @Test
	    void testEquality_DifferentValue() {
	    	QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
	    	QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);

	        assertFalse(f1.equals(f2), "1.0 ft should not equal 2.0 ft");
	    }

	    @Test
	    void testEquality_NullComparison() {
	    	QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

	        assertFalse(f1.equals(null), "Value should not equal null");
	    }

	    @Test
	    void testEquality_SameReference() {
	    	QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

	        assertTrue(f1.equals(f1), "Object should equal itself");
	    }

	    @Test
	    void testEquality_NonNumericInput() {
	    	QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
	        String other = "test";

	        assertFalse(f1.equals(other), "Feet object should not equal non-numeric object");
	    }
	}


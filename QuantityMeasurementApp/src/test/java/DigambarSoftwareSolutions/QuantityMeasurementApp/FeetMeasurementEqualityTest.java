package DigambarSoftwareSolutions.QuantityMeasurementApp;

	import static org.junit.jupiter.api.Assertions.*;
	import org.junit.jupiter.api.Test;

	public class FeetMeasurementEqualityTest {

	    @Test
	    void testEquality_SameValue() {
	    	FeetMeasurementEquality.Feet f1 = new FeetMeasurementEquality.Feet(1.0);
	    	FeetMeasurementEquality.Feet f2 = new FeetMeasurementEquality.Feet(1.0);

	        assertTrue(f1.equals(f2), "1.0 ft should equal 1.0 ft");
	    }

	    @Test
	    void testEquality_DifferentValue() {
	    	FeetMeasurementEquality.Feet f1 = new FeetMeasurementEquality.Feet(1.0);
	    	FeetMeasurementEquality.Feet f2 = new FeetMeasurementEquality.Feet(2.0);

	        assertFalse(f1.equals(f2), "1.0 ft should not equal 2.0 ft");
	    }

	    @Test
	    void testEquality_NullComparison() {
	    	FeetMeasurementEquality.Feet f1 = new FeetMeasurementEquality.Feet(1.0);

	        assertFalse(f1.equals(null), "Value should not equal null");
	    }

	    @Test
	    void testEquality_SameReference() {
	    	FeetMeasurementEquality.Feet f1 = new FeetMeasurementEquality.Feet(1.0);

	        assertTrue(f1.equals(f1), "Object should equal itself");
	    }

	    @Test
	    void testEquality_NonNumericInput() {
	    	FeetMeasurementEquality.Feet f1 = new FeetMeasurementEquality.Feet(1.0);
	        String other = "test";

	        assertFalse(f1.equals(other), "Feet object should not equal non-numeric object");
	    }
	}


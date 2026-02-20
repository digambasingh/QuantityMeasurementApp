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
	    
	    @Test
	    void testInchesEquality_SameValue() {
	        QuantityMeasurementApp.Inches i1 =
	                new QuantityMeasurementApp.Inches(1.0);
	        QuantityMeasurementApp.Inches i2 =
	                new QuantityMeasurementApp.Inches(1.0);

	        assertTrue(i1.equals(i2),
	                "1.0 inch should equal 1.0 inch");
	    }
	    
	    @Test
	    void testInchesEquality_DifferentValue() {
	        QuantityMeasurementApp.Inches i1 =
	                new QuantityMeasurementApp.Inches(1.0);
	        QuantityMeasurementApp.Inches i2 =
	                new QuantityMeasurementApp.Inches(2.0);

	        assertFalse(i1.equals(i2),
	                "1.0 inch should not equal 2.0 inch");
	    }
	    
	    @Test
	    void testInchesEquality_NullComparison() {
	        QuantityMeasurementApp.Inches i1 =
	                new QuantityMeasurementApp.Inches(1.0);

	        assertFalse(i1.equals(null),
	                "Inches should not equal null");
	    }
	    
	    @Test
	    void testInchesEquality_DifferentClass() {
	        QuantityMeasurementApp.Inches i1 =
	                new QuantityMeasurementApp.Inches(1.0);

	        String other = "abc";

	        assertFalse(i1.equals(other),
	                "Inches should not equal different class");
	    }
	    
	    @Test
	    void testInchesEquality_SameReference() {
	        QuantityMeasurementApp.Inches i1 =
	                new QuantityMeasurementApp.Inches(1.0);

	        assertTrue(i1.equals(i1),
	                "Object should equal itself");
	    }
	    
	}


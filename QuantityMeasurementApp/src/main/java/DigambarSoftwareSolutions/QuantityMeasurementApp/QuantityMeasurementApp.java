package DigambarSoftwareSolutions.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

    	Length l1 = new Length(1, LengthUnit.FEET);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	System.out.println(l1.equals(l2));
    	
    	Length l3 = new Length(1, LengthUnit.YARDS);
    	Length l4 = new Length(36, LengthUnit.INCHES);
    	System.out.println(l3.equals(l4)); 
    	
    	Length l5 = new Length(100, LengthUnit.CENTIMETERS);
    	Length l6 = new Length(39.3701,LengthUnit.INCHES);
    	System.out.println(l5.equals(l6));
    }
}

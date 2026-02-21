package DigambarSoftwareSolutions.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

    	Length l1 = new Length(1, LengthUnit.FEET);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	
    	System.out.println(l1.equals(l2));
    }
}

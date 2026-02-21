package DigambarSoftwareSolutions.QuantityMeasurementApp;

public enum LengthUnit {
	FEET(12.0),
	INCHES(1.0),
	YARDS(36.0),     // 1 yard = 36 inches
	CENTIMETERS(0.393701);              // 1cm = 0.393701 inches
	
	private final double conversionFactor;
	
	LengthUnit(double conversionFactor){
		this.conversionFactor = conversionFactor;
	}
	
	public double getConversionFactor() {
		return conversionFactor;
	}
}

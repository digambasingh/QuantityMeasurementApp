package DigambarSoftwareSolutions.QuantityMeasurementApp;

import java.util.Objects;

public class Length {
		 
	private final double value;
	private final LengthUnit unit;
	
	public Length(double value, LengthUnit unit) {
		this.value = value;
		this.unit = unit;
	}
	
	// Convert any length to base unit (inches)
	private double toBaseUnit() {
//		return value * unit.getConversionFactor();
		double result = value * unit.getConversionFactor();
		return Math.round(result * 10000.0) /10000.0;
	}
	
	// compare to lengths
	
	public boolean compare(Length other) {
		return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
	}
	
	@Override
	 public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Length)) {
			return false;
		}
		
		Length other = (Length) obj;
		return compare(other);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(toBaseUnit());
	}
		
}

package DigambarSoftwareSolutions.QuantityMeasurementApp;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {
	// Default lambda: all units support arithmetic unless overridden
	SupportsArithmetic supportsArithmetic = () -> true;
	
    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
    
    // ---------- Optional Methods ----------

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }
    
    default void validateOperationSupport(String operation) {
        // Default: allow operations
    }
}
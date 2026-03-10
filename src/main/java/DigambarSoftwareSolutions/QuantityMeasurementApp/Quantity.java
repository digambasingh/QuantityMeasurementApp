package DigambarSoftwareSolutions.QuantityMeasurementApp;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;
    private static final double EPSILON = 1e-6;
    
    public Quantity(double value, U unit) {

        if(unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException("Invalid quantity");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other)) return false;

        if (unit.getClass() != other.unit.getClass()) return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.getUnitName());
    }
    
    
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(convertedValue, targetUnit);
    }
    
    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }
    
    // Addition Method
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performArithmetic(other, targetUnit, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }

    // Subtraction method 
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }
    
    // Division method
    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performArithmetic(other, null, ArithmeticOperation.DIVIDE);
    }
    
    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if (unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Incompatible unit types");

        if (!Double.isFinite(value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Values must be finite");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit required");
    }
   
    private double performArithmetic(
            Quantity<U> other,
            U targetUnit,
            ArithmeticOperation operation) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }
    
    private enum ArithmeticOperation {

        ADD((a,b)->a+b),

        SUBTRACT((a,b)->a-b),

        DIVIDE((a,b)->{
            if(b==0) throw new ArithmeticException("Divide by zero");
            return a/b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation){
            this.operation=operation;
        }

        public double compute(double a,double b){
            return operation.applyAsDouble(a,b);
        }
    }
    
    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
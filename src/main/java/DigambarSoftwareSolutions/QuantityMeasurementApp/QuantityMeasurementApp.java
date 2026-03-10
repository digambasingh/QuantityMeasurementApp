package DigambarSoftwareSolutions.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // Length Example
        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Are lengths equal? " +
                length1.equals(length2));

        Quantity<LengthUnit> convertedLength =
                length1.convertTo(LengthUnit.INCHES);

        System.out.println("1 foot in inches: " + convertedLength);


        // Addition Example
        Quantity<LengthUnit> totalLength =
                length1.add(length2, LengthUnit.FEET);

        System.out.println("Total Length: " + totalLength);


        // Weight Example
        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Are weights equal? " +
                weight1.equals(weight2));

        Quantity<WeightUnit> totalWeight =
                weight1.add(weight2, WeightUnit.KILOGRAM);

        System.out.println("Total Weight: " + totalWeight);
    }
}
package DigambarSoftwareSolutions.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static Length add(Length l1, Length l2, LengthUnit targetUnit) {

        if (l1 == null || l2 == null)
            throw new IllegalArgumentException("Length cannot be null");

        return l1.add(l2, targetUnit);
    }

    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        Length length = new Length(value, source);
        return length.convertTo(target).getValue();
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println(l1.convertTo(LengthUnit.INCHES));

        System.out.println(l1.add(l2, LengthUnit.FEET));

        System.out.println(
                new Length(36.0, LengthUnit.INCHES)
                        .equals(new Length(1.0, LengthUnit.YARDS))
        );
        
        
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2)); 

        System.out.println(
                new Weight(2.0, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM)
        );

        System.out.println(
                w1.add(w2, WeightUnit.GRAM)
        );
    }
}
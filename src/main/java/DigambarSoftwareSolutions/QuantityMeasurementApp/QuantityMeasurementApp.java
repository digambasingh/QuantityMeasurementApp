package DigambarSoftwareSolutions.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    // equality demo
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    // comparison using values
    public static boolean demonstrateLengthComparison(double v1, LengthUnit u1,
                                                      double v2, LengthUnit u2) {

        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);

        return demonstrateLengthEquality(l1, l2);
    }

    // Overload 1 -> raw conversion
    public static Length demonstrateLengthConversion(double value,
                                                     LengthUnit from,
                                                     LengthUnit to) {

        Length length = new Length(value, from);
        return length.convertTo(to);
    }

    // Overload 2 -> object conversion
    public static Length demonstrateLengthConversion(Length length,
                                                     LengthUnit to) {

        return length.convertTo(to);
    }

    // Required API for UC5 tests
    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (source == null || target == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        Length length = new Length(value, source);
        Length converted = length.convertTo(target);

        return converted.getValue();
    }
 // UC6 Addition API
    public static Length add(Length l1, Length l2) {

        if (l1 == null || l2 == null)
            throw new IllegalArgumentException("Length cannot be null");

        return l1.add(l2);
    }
    
 // UC7 Addition API with explicit target unit
    public static Length add(Length l1, Length l2, LengthUnit targetUnit) {

        if (l1 == null || l2 == null)
            throw new IllegalArgumentException("Length cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return l1.add(l2, targetUnit);
    }

    public static void main(String[] args) {

        System.out.println("1 foot == 12 inch : " +
                demonstrateLengthComparison(1,
                        LengthUnit.FEET,
                        12,
                        LengthUnit.INCHES));

        System.out.println("3 feet -> inches : " +
                convert(3,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));

        System.out.println("1 yard -> inches : " +
                convert(1,
                        LengthUnit.YARDS,
                        LengthUnit.INCHES));
        
        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(12, LengthUnit.INCHES);

        System.out.println(l1.add(l2));
        
        System.out.println(add(l1, l2, LengthUnit.FEET));
        System.out.println(add(l1, l2, LengthUnit.INCHES));
        System.out.println(add(l1, l2, LengthUnit.YARDS));
        
    }
}
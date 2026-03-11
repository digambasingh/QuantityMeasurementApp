package DigambarSoftwareSolutions.QuantityMeasurementApp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

    	 Quantity<TemperatureUnit> temp1 =
                 new Quantity<>(0, TemperatureUnit.CELSIUS);

         Quantity<TemperatureUnit> temp2 =
                 new Quantity<>(32, TemperatureUnit.FAHRENHEIT);

         System.out.println("0°C equals 32°F: " + temp1.equals(temp2));

         Quantity<TemperatureUnit> boiling =
                 new Quantity<>(100, TemperatureUnit.CELSIUS);

         Quantity<TemperatureUnit> converted =
                 boiling.convertTo(TemperatureUnit.FAHRENHEIT);

         System.out.println("100°C = " + converted.getValue() + "°F");

         try {
             boiling.add(new Quantity<>(50, TemperatureUnit.CELSIUS));
         } catch (UnsupportedOperationException e) {
             System.out.println(e.getMessage());
         }
        
    }
}
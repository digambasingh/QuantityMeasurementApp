package bridgeLabz.quantity_measurement.strategy;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;

public interface ConversionStrategy {

    double toBase(double value, String unit);

    double fromBase(double baseValue, String unit);

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2);

    boolean compare(QuantityDTO q1, QuantityDTO q2);
}

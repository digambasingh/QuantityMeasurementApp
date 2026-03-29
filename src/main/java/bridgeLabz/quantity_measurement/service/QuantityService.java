package bridgeLabz.quantity_measurement.service;


import bridgeLabz.quantity_measurement.dto.QuantityDTO;

public interface QuantityService {

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2);

    boolean compare(QuantityDTO q1, QuantityDTO q2);

    QuantityDTO convert(QuantityDTO q, String targetUnit);
}

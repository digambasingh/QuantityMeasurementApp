package bridgeLabz.quantity_measurement.service;


import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.entity.QuantityMeasurementEntity;

import java.util.List;

public interface QuantityService {

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO multiply(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO divide(QuantityDTO q1, QuantityDTO q2);

    boolean compare(QuantityDTO q1, QuantityDTO q2);

    QuantityDTO convert(QuantityDTO q, String targetUnit);

    List<QuantityMeasurementEntity> getAll();
}

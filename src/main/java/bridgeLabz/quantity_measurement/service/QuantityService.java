package bridgeLabz.quantity_measurement.service;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.entity.QuantityMeasurementEntity;
import bridgeLabz.quantity_measurement.entity.UserEntity;

import java.util.List;

public interface QuantityService {

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2, UserEntity user);
    QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, UserEntity user);
    QuantityDTO multiply(QuantityDTO q1, QuantityDTO q2, UserEntity user);
    QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, UserEntity user);

    boolean compare(QuantityDTO q1, QuantityDTO q2, UserEntity user);

    QuantityDTO convert(QuantityDTO q, String targetUnit, UserEntity user);

    List<QuantityMeasurementEntity> getByUser(UserEntity user);
}

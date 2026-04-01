package bridgeLabz.quantity_measurement.entity;

import lombok.Data;

@Data
public class QuantityMeasurementHistory {

    private Long id;
    private Long entityId;
    private Integer operationCount;
}
package bridgeLabz.quantity_measurement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class QuantityDTO {

    private final double value;
    private final String unitName;
    private final String measurementType;

}
package bridgeLabz.quantity_measurement.dto.request;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequestDTO {

    @Valid
    private QuantityDTO firstQuantity;

    @Valid
    private QuantityDTO secondQuantity;
}

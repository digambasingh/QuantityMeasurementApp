package bridgeLabz.quantity_measurement.request;


import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRequest {

    @Valid
    private QuantityDTO firstQuantity;

    @Valid
    private QuantityDTO secondQuantity;

}

package bridgeLabz.quantity_measurement.request;


import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRequest {

    private QuantityDTO firstQuantity;
    private QuantityDTO secondQuantity;

}

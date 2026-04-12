package bridgeLabz.quantity_measurement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityDTO {

    @NotNull(message = "Value cannot be null")
    @Positive(message = "Value must be positive")
    private Double value;

    @NotBlank(message = "Unit cannot be empty")
    private String unitName;

    @NotBlank(message = "Measurement type cannot be empty")
    private String measurementType;
}
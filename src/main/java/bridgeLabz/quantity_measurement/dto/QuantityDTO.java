package bridgeLabz.quantity_measurement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
public final class QuantityDTO {

    @NotNull(message = "Value cannot be null")
    @Positive(message = "Value must be positive")
    private final double value;

    @NotBlank(message = "Unit cannot be empty")
    private final String unitName;

    @NotBlank(message = "Measurement type cannot be empty")
    private final String measurementType;

}
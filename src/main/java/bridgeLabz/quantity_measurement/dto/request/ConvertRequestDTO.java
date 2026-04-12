package bridgeLabz.quantity_measurement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvertRequestDTO {

    @NotNull(message = "Value cannot be null")
    @Positive(message = "Value must be positive")
    private Double value;

    @NotBlank(message = "Unit name is required")
    private String unitName;

    @NotBlank(message = "Measurement type is required")
    private String measurementType;

    @NotBlank(message = "Target unit is required")
    private String targetUnit;
}

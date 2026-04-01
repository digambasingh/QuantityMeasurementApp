package bridgeLabz.quantity_measurement.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuantityMeasurementEntity {

    private Long id;

    // 🔹 Input 1
    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;

    // 🔹 Input 2 (optional for convert)
    private Double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    // 🔹 Operation (ADD, COMPARE, CONVERT)
    private String operation;

    // 🔹 Result
    private Double resultValue;
    private String resultUnit;
    private String resultMeasurementType;
    private String resultString;

    // 🔹 Error Handling
    private boolean isError;
    private String errorMessage;

    // 🔹 Audit Fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
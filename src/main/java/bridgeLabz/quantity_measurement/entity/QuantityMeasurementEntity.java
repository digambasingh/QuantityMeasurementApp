package bridgeLabz.quantity_measurement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "quantity_measurement_entity")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Input 1
    @Column(name = "this_value")
    private double thisValue;

    @Column(name = "this_unit")
    private String thisUnit;

    @Column(name = "this_measurement_type")
    private String thisMeasurementType;

    // 🔹 Input 2 (optional for convert)
    @Column(name = "that_value")
    private Double thatValue;

    @Column(name = "that_unit")
    private String thatUnit;

    @Column(name = "that_measurement_type")
    private String thatMeasurementType;

    // 🔹 Operation (ADD, SUBTRACT, MULTIPLY, DIVIDE, COMPARE, CONVERT)
    @Column(nullable = false)
    private String operation;

    // 🔹 Result
    @Column(name = "result_value")
    private Double resultValue;

    @Column(name = "result_unit")
    private String resultUnit;

    @Column(name = "result_measurement_type")
    private String resultMeasurementType;

    @Column(name = "result_string")
    private String resultString;

    // 🔹 Error Handling
    @Column(name = "is_error")
    private boolean isError;

    @Column(name = "error_message")
    private String errorMessage;

    // 🔹 User association
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // 🔹 Audit Fields
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
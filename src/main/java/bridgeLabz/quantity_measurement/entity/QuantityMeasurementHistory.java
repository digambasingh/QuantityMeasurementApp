package bridgeLabz.quantity_measurement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "quantity_measurement_history")
public class QuantityMeasurementHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false, unique = true)
    private QuantityMeasurementEntity entity;

    @Column(name = "operation_count", nullable = false)
    private Integer operationCount = 1;
}
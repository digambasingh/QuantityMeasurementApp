package bridgeLabz.quantity_measurement.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuantityHistoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveOrUpdate(Long entityId) {

        String checkSql = "SELECT COUNT(*) FROM quantity_measurement_history WHERE entity_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, entityId);

        if (count != null && count > 0) {
            String updateSql = """
                UPDATE quantity_measurement_history
                SET operation_count = operation_count + 1
                WHERE entity_id = ?
            """;
            jdbcTemplate.update(updateSql, entityId);

        } else {
            String insertSql = """
                INSERT INTO quantity_measurement_history (entity_id, operation_count)
                VALUES (?, 1)
            """;
            jdbcTemplate.update(insertSql, entityId);
        }
    }
}
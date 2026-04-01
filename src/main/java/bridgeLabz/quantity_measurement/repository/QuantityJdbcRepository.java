package bridgeLabz.quantity_measurement.repository;

import bridgeLabz.quantity_measurement.entity.QuantityMeasurementEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuantityJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

//    public void save(QuantityMeasurementEntity e) {
//
//        String sql = """
//            INSERT INTO quantity_measurement_entity
//            (this_value, this_unit, this_measurement_type,
//             that_value, that_unit, that_measurement_type,
//             operation, result_value, result_unit,
//             result_measurement_type, is_error, error_message)
//            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
//        """;
//
//        jdbcTemplate.update(sql,
//                e.getThisValue(),
//                e.getThisUnit(),
//                e.getThisMeasurementType(),
//
//                e.getThatValue(),
//                e.getThatUnit(),
//                e.getThatMeasurementType(),
//
//                e.getOperation(),
//
//                e.getResultValue(),
//                e.getResultUnit(),
//                e.getResultMeasurementType(),
//
//                e.isError(),
//                e.getErrorMessage()
//        );
//    }

    public Long saveAndReturnId(QuantityMeasurementEntity e) {

        String sql = """
        INSERT INTO quantity_measurement_entity
        (this_value, this_unit, this_measurement_type,
         that_value, that_unit, that_measurement_type,
         operation, result_value, result_unit,
         result_measurement_type, is_error, error_message)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDouble(1, e.getThisValue());
            ps.setString(2, e.getThisUnit());
            ps.setString(3, e.getThisMeasurementType());

            ps.setObject(4, e.getThatValue());
            ps.setString(5, e.getThatUnit());
            ps.setString(6, e.getThatMeasurementType());

            ps.setString(7, e.getOperation());

            ps.setObject(8, e.getResultValue());
            ps.setString(9, e.getResultUnit());
            ps.setString(10, e.getResultMeasurementType());

            ps.setBoolean(11, e.isError());
            ps.setString(12, e.getErrorMessage());

            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
    public List<QuantityMeasurementEntity> findAll() {

        String sql = "SELECT * FROM quantity_measurement_entity";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            QuantityMeasurementEntity e = new QuantityMeasurementEntity();

            e.setId(rs.getLong("id"));
            e.setThisValue(rs.getDouble("this_value"));
            e.setThisUnit(rs.getString("this_unit"));
            e.setThisMeasurementType(rs.getString("this_measurement_type"));

            e.setThatValue(rs.getObject("that_value", Double.class));
            e.setThatUnit(rs.getString("that_unit"));
            e.setThatMeasurementType(rs.getString("that_measurement_type"));

            e.setOperation(rs.getString("operation"));

            e.setResultValue(rs.getDouble("result_value"));
            e.setResultUnit(rs.getString("result_unit"));
            e.setResultMeasurementType(rs.getString("result_measurement_type"));

            e.setError(rs.getBoolean("is_error"));
            e.setErrorMessage(rs.getString("error_message"));

            return e;
        });
    }

    public List<QuantityMeasurementEntity> findByOperation(String operation) {

        String sql = "SELECT * FROM quantity_measurement_entity WHERE operation = ?";

        return jdbcTemplate.query(sql, new Object[]{operation},
                (rs, rowNum) -> {
                    QuantityMeasurementEntity e = new QuantityMeasurementEntity();
                    e.setId(rs.getLong("id"));
                    e.setOperation(rs.getString("operation"));
                    return e;
                });
    }
}
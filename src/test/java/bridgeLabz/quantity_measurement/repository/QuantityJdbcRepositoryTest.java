package bridgeLabz.quantity_measurement.repository;

import bridgeLabz.quantity_measurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuantityJdbcRepositoryTest {

    @Autowired
    private QuantityJdbcRepository repo;

    //  Test Save + Fetch
    @Test
    void testSaveAndFindAll() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(5.0);
        entity.setThisUnit("FEET");
        entity.setThisMeasurementType("LengthUnit");

        entity.setThatValue(12.0);
        entity.setThatUnit("INCHES");
        entity.setThatMeasurementType("LengthUnit");

        entity.setOperation("ADD");

        entity.setResultValue(6.0);
        entity.setResultUnit("FEET");
        entity.setResultMeasurementType("LengthUnit");

        entity.setError(false);

        Long id = repo.saveAndReturnId(entity);

        assertNotNull(id);

        List<QuantityMeasurementEntity> list = repo.findAll();

        assertFalse(list.isEmpty());
    }

    //  Test Find By Operation
    @Test
    void testFindByOperation() {

        List<QuantityMeasurementEntity> list = repo.findByOperation("ADD");

        assertNotNull(list);
    }

    //  Test Save Error Case
    @Test
    void testSaveErrorCase() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(5.0);
        entity.setThisUnit("INVALID");
        entity.setThisMeasurementType("LengthUnit");

        entity.setOperation("ADD");

        entity.setError(true);
        entity.setErrorMessage("Invalid unit");

        Long id = repo.saveAndReturnId(entity);

        assertNotNull(id);
    }
}
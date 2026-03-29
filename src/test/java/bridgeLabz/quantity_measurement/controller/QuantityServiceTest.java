package bridgeLabz.quantity_measurement.controller;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.service.QuantityService;
import bridgeLabz.quantity_measurement.service.impl.QuantityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class QuantityServiceTest {

    @Autowired
    private QuantityService service;

    @Test
    void testAdd_Length_Success() {

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LengthUnit");

        QuantityDTO result = service.add(q1, q2);

        assertEquals(2, result.getValue(), 0.01);
    }

    @Test
    void testCompare_Length_Success() {

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LengthUnit");

        boolean result = service.compare(q1, q2);

        assertTrue(result);
    }

    @Test
    void testConvert_Length_Success() {

        QuantityDTO q = new QuantityDTO(1, "FEET", "LengthUnit");

        QuantityDTO result = service.convert(q, "INCHES");

        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    void testAdd_CategoryMismatch_Error() {

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(1, "KG", "WeightUnit");

        assertThrows(RuntimeException.class, () -> service.add(q1, q2));
    }
}
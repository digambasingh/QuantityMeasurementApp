package bridgeLabz.quantity_measurement.service;

import bridgeLabz.quantity_measurement.dto.QuantityDTO;
import bridgeLabz.quantity_measurement.factory.StrategyFactory;
import bridgeLabz.quantity_measurement.repository.QuantityHistoryRepository;
import bridgeLabz.quantity_measurement.repository.QuantityJdbcRepository;
import bridgeLabz.quantity_measurement.service.impl.QuantityServiceImpl;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuantityServiceImplTest {

    private QuantityJdbcRepository repo;
    private QuantityHistoryRepository historyRepo;
    private StrategyFactory factory;
    private ConversionStrategy strategy;

    private QuantityServiceImpl service;

    @BeforeEach
    void setup() {
        repo = mock(QuantityJdbcRepository.class);
        historyRepo = mock(QuantityHistoryRepository.class);
        factory = mock(StrategyFactory.class);
        strategy = mock(ConversionStrategy.class);

        service = new QuantityServiceImpl(factory, repo, historyRepo);
    }

    @Test
    void testAddSuccess() {
        QuantityDTO q1 = new QuantityDTO(5, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LengthUnit");

        QuantityDTO result = new QuantityDTO(6, "FEET", "LengthUnit");

        when(factory.getStrategy("LengthUnit")).thenReturn(strategy);
        when(strategy.add(q1, q2)).thenReturn(result);
        when(repo.saveAndReturnId(any())).thenReturn(1L);

        QuantityDTO response = service.add(q1, q2);

        assertEquals(6, response.getValue());
        verify(historyRepo).saveOrUpdate(1L);
    }

    @Test
    void testAddNullInput() {
        assertThrows(RuntimeException.class,
                () -> service.add(null, null));
    }

    @Test
    void testAddDifferentMeasurementType() {
        QuantityDTO q1 = new QuantityDTO(5, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(5, "KG", "WeightUnit");

        assertThrows(RuntimeException.class,
                () -> service.add(q1, q2));
    }

    @Test
    void testCompareTrue() {
        QuantityDTO q1 = new QuantityDTO(1, "METER", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(100, "CM", "LengthUnit");

        when(factory.getStrategy("LengthUnit")).thenReturn(strategy);
        when(strategy.compare(q1, q2)).thenReturn(true);

        boolean result = service.compare(q1, q2);

        assertTrue(result);
    }

    @Test
    void testConvert() {
        QuantityDTO q = new QuantityDTO(1, "METER", "LengthUnit");

        when(factory.getStrategy("LengthUnit")).thenReturn(strategy);
        when(strategy.toBase(1, "METER")).thenReturn(1.0);
        when(strategy.fromBase(1.0, "CM")).thenReturn(100.0);

        QuantityDTO result = service.convert(q, "CM");

        assertEquals(100.0, result.getValue());
    }
}
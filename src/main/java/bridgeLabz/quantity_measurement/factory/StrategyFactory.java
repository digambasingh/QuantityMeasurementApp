package bridgeLabz.quantity_measurement.factory;

import bridgeLabz.quantity_measurement.exception.InvalidUnitException;
import bridgeLabz.quantity_measurement.strategy.ConversionStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StrategyFactory {

    private final Map<String, ConversionStrategy> strategyMap;

    public StrategyFactory(Map<String, ConversionStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public ConversionStrategy getStrategy(String type) {
        if (type == null || type.isBlank()) {
            throw new InvalidUnitException("Measurement type cannot be null or empty");
        }

        ConversionStrategy strategy = strategyMap.get(type.trim());

        if (strategy == null) {
            throw new InvalidUnitException("Invalid measurement type: " + type
                    + ". Supported types: " + strategyMap.keySet());
        }

        return strategy;
    }
}
package bridgeLabz.quantity_measurement.factory;

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

        ConversionStrategy strategy = strategyMap.get(type);

        if (strategy == null) {
            throw new RuntimeException("Invalid measurement type: " + type);
        }

        return strategy;
    }
}
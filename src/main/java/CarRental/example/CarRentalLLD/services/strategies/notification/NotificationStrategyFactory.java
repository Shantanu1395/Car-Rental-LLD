package CarRental.example.CarRentalLLD.services.strategies.notification;

import CarRental.example.CarRentalLLD.interfaces.strategy.NotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationStrategyFactory {

    private final Map<String, NotificationStrategy> strategyMap;

    @Autowired
    public NotificationStrategyFactory(Map<String, NotificationStrategy> strategies) {
        this.strategyMap = strategies;
    }

    public NotificationStrategy getStrategy(String type) {
        NotificationStrategy strategy = strategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for type: " + type);
        }
        return strategy;
    }
}

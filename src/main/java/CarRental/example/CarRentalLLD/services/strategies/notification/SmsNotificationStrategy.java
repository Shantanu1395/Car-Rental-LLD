package CarRental.example.CarRentalLLD.services.strategies.notification;

import CarRental.example.CarRentalLLD.interfaces.strategy.NotificationStrategy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component("sms")
public class SmsNotificationStrategy implements NotificationStrategy {

    @Async
    @Override
    public void sendNotification(Long userId, String message) {
        System.out.println("Sending SMS: " + message);
    }
}

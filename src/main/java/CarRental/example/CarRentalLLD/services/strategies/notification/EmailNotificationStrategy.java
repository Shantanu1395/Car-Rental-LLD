package CarRental.example.CarRentalLLD.services.strategies.notification;

import CarRental.example.CarRentalLLD.interfaces.strategy.NotificationStrategy;
import CarRental.example.CarRentalLLD.models.User;
import CarRental.example.CarRentalLLD.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component("email")
public class EmailNotificationStrategy implements NotificationStrategy {

    @Autowired
    private UserRepository userRepository;

    @Async
    @Override
    public void sendNotification(Long userId, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        String email = user.getEmail();

        // Simulating sending an email
        System.out.println("Sending email to: " + email);
        System.out.println("Message: " + message);
    }
}

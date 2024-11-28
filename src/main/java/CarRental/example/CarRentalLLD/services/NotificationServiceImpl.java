package CarRental.example.CarRentalLLD.services;

import CarRental.example.CarRentalLLD.interfaces.strategy.NotificationService;
import CarRental.example.CarRentalLLD.interfaces.strategy.NotificationStrategy;
import CarRental.example.CarRentalLLD.models.Booking;
import CarRental.example.CarRentalLLD.models.User;
import CarRental.example.CarRentalLLD.repositories.BookingRepository;
import CarRental.example.CarRentalLLD.repositories.UserRepository;
import CarRental.example.CarRentalLLD.services.strategies.notification.NotificationStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final NotificationStrategyFactory strategyFactory;

    @Autowired
    public NotificationServiceImpl(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            NotificationStrategyFactory strategyFactory
    ) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.strategyFactory = strategyFactory;
    }

    @Override
    public void sendPickupNotification(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        User user = userRepository.findById(booking.getUser().getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        NotificationStrategy strategy = strategyFactory.getStrategy("sms");
        strategy.sendNotification(user.getId(), "Your vehicle is ready for pickup.");
    }

    @Override
    public void sendReturnNotification(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        User user = userRepository.findById(booking.getUser().getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        NotificationStrategy strategy = strategyFactory.getStrategy("sms");
        strategy.sendNotification(user.getId(), "Please return the vehicle by the due date.");
    }
}

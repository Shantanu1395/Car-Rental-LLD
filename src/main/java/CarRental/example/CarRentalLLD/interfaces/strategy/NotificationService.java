package CarRental.example.CarRentalLLD.interfaces.strategy;

public interface NotificationService {
    void sendPickupNotification(Long bookingId);
    void sendReturnNotification(Long bookingId);
}

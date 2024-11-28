package CarRental.example.CarRentalLLD.interfaces;

import CarRental.example.CarRentalLLD.models.Payment;

import java.util.List;

public interface PaymentService {
    Payment processPayment(Long bookingId, double amount, String paymentMethod);
    List<Payment> getPaymentsForBooking(Long bookingId);
}


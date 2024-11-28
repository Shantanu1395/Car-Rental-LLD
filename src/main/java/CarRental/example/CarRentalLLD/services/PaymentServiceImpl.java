package CarRental.example.CarRentalLLD.services;

import CarRental.example.CarRentalLLD.interfaces.PaymentService;
import CarRental.example.CarRentalLLD.models.Booking;
import CarRental.example.CarRentalLLD.models.Payment;
import CarRental.example.CarRentalLLD.repositories.BookingRepository;
import CarRental.example.CarRentalLLD.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Payment processPayment(Long bookingId, double amount, String paymentMethod) {

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found: " + bookingId));

        Payment payment = new Payment(booking, amount, paymentMethod, "SUCCESS");
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsForBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }
}

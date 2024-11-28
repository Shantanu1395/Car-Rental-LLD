package CarRental.example.CarRentalLLD.repositories;

import CarRental.example.CarRentalLLD.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Find payments by booking ID
    List<Payment> findByBookingId(Long bookingId);
}
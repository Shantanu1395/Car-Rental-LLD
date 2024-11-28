package CarRental.example.CarRentalLLD.repositories;

import CarRental.example.CarRentalLLD.models.BookingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingEventRepository extends JpaRepository<BookingEvent, Long> {
    // Log events related to a booking
    List<BookingEvent> findByBookingId(Long bookingId);
}

package CarRental.example.CarRentalLLD.repositories;

import CarRental.example.CarRentalLLD.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Find bookings by user ID
    List<Booking> findByUserId(Long userId);

    // Find bookings by vehicle ID
    List<Booking> findByVehicleId(Long vehicleId);

    // Find active bookings by vehicle ID
    Optional<Booking> findByVehicleIdAndStatus(Long vehicleId, String status);
}

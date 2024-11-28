package CarRental.example.CarRentalLLD.repositories;

import CarRental.example.CarRentalLLD.models.AddOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddOnRepository extends JpaRepository<AddOn, Long> {
    // Find add-ons by booking ID
    @Query("SELECT a FROM AddOn a JOIN BookingAddOn ba ON a.id = ba.addOnId WHERE ba.bookingId = :bookingId")
    List<AddOn> findAddOnsByBookingId(@Param("bookingId") Long bookingId);
}
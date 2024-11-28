package CarRental.example.CarRentalLLD.interfaces;

import CarRental.example.CarRentalLLD.models.Booking;
import CarRental.example.CarRentalLLD.models.Fee;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    Booking createBooking(Long userId, Long vehicleId, LocalDateTime startTime, LocalDateTime endTime, List<Long> addOnIds);
    Booking modifyBooking(Long bookingId, LocalDateTime newStartTime, LocalDateTime newEndTime, List<Long> addOnIds);
    void cancelBooking(Long bookingId);
    Booking getBookingById(Long bookingId);
    List<Fee> calculateFeesForBooking(Long bookingId);
    double getTotalFee(Long bookingId);
}

package CarRental.example.CarRentalLLD.controllers;

import CarRental.example.CarRentalLLD.dtos.BookingRequest;
import CarRental.example.CarRentalLLD.interfaces.BookingService;
import CarRental.example.CarRentalLLD.models.Booking;
import CarRental.example.CarRentalLLD.models.Fee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request) {
        Booking createdBooking = bookingService.createBooking(
                request.getUserId(),
                request.getVehicleId(),
                request.getStartTime(),
                request.getEndTime(),
                request.getAddOnIds()
        );
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> modifyBooking(@PathVariable Long id, @RequestBody BookingRequest request) {
        Booking updatedBooking = bookingService.modifyBooking(id, request.getStartTime(), request.getEndTime(), request.getAddOnIds());
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}/fees")
    public ResponseEntity<List<Fee>> getBookingFees(@PathVariable Long id) {
        List<Fee> fees = bookingService.calculateFeesForBooking(id);
        return ResponseEntity.ok(fees);
    }

    @GetMapping("/{id}/total-fee")
    public ResponseEntity<Double> getTotalFee(@PathVariable Long id) {
        double totalFee = bookingService.getTotalFee(id);
        return ResponseEntity.ok(totalFee);
    }
}

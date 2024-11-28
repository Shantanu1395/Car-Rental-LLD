package CarRental.example.CarRentalLLD.controllers;

import CarRental.example.CarRentalLLD.interfaces.BookingEventService;
import CarRental.example.CarRentalLLD.models.BookingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking-events")
public class BookingEventController {

    @Autowired
    private BookingEventService bookingEventService;

    @PostMapping("/{bookingId}")
    public ResponseEntity<Void> logEvent(@PathVariable Long bookingId, @RequestParam String event, @RequestParam String description) {
        bookingEventService.logEvent(bookingId, event, description);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<List<BookingEvent>> getEventsForBooking(@PathVariable Long bookingId) {
        List<BookingEvent> events = bookingEventService.getEventsForBooking(bookingId);
        return ResponseEntity.ok(events);
    }
}

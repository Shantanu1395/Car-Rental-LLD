package CarRental.example.CarRentalLLD.interfaces;

import CarRental.example.CarRentalLLD.models.BookingEvent;

import java.util.List;

public interface BookingEventService {
    void logEvent(Long bookingId, String event, String description);
    List<BookingEvent> getEventsForBooking(Long bookingId);
}

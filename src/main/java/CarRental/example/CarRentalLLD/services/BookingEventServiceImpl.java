package CarRental.example.CarRentalLLD.services;

import CarRental.example.CarRentalLLD.interfaces.BookingEventService;
import CarRental.example.CarRentalLLD.models.Booking;
import CarRental.example.CarRentalLLD.models.BookingEvent;
import CarRental.example.CarRentalLLD.repositories.BookingEventRepository;
import CarRental.example.CarRentalLLD.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingEventServiceImpl implements BookingEventService {

    private final BookingEventRepository bookingEventRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingEventServiceImpl(BookingEventRepository bookingEventRepository, BookingRepository bookingRepository) {
        this.bookingEventRepository = bookingEventRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void logEvent(Long bookingId, String event, String description) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException("Booking not found: " + bookingId));
        BookingEvent bookingEvent = new BookingEvent(booking, event, description, LocalDateTime.now());
        bookingEventRepository.save(bookingEvent);
    }

    @Override
    public List<BookingEvent> getEventsForBooking(Long bookingId) {
        return bookingEventRepository.findByBookingId(bookingId);
    }
}
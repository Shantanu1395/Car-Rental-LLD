package CarRental.example.CarRentalLLD.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BookingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String event;
    private String description;
    private LocalDateTime timestamp;

    public BookingEvent() {
    }

    public BookingEvent(Booking booking, String event, String description, LocalDateTime timestamp) {
        this.booking = booking;
        this.event = event;
        this.description = description;
        this.timestamp = timestamp;
    }

    // Getters and Setters
}

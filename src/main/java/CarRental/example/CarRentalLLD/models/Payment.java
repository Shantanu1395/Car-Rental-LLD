package CarRental.example.CarRentalLLD.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private double amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime timestamp;

    public Payment() {
    }

    public Payment(Booking booking, double amount, String paymentMethod, String status) {
        this.booking = booking;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
}

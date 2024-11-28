package CarRental.example.CarRentalLLD.models;

import javax.persistence.*;

@Entity
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String type;
    private double amount;

    public Fee() {
    }

    public Fee(Booking booking, String type, double amount) {
        this.booking = booking;
        this.type = type;
        this.amount = amount;
    }

    // Getters and Setters

    public double getAmount() {
        return amount;
    }
}

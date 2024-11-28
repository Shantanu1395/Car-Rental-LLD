package CarRental.example.CarRentalLLD.models;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Fee> fees;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingEvent> events;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @ManyToMany
    @JoinTable(
            name = "booking_addon",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "addon_id")
    )
    private List<AddOn> addOns;

    public Booking(User user, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.user = user;
        this.vehicle = vehicle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters and Setters

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    // Utils
    public double getDuration() {
        if (startTime == null || endTime == null) {
            throw new IllegalStateException("Start time and end time must not be null.");
        }

        Duration duration = Duration.between(startTime, endTime);
        return duration.toHours() + (duration.toMinutesPart() / 60.0);
    }

}

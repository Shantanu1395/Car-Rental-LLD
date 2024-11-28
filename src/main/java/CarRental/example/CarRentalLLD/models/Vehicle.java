package CarRental.example.CarRentalLLD.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String type;
    private String barcode;
    private boolean isAvailable;
    private double fuelLevel;
    private double mileage;
    private LocalDateTime lastServiced;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public Vehicle(String licensePlate, String type, String barcode, Branch branch, boolean isAvailable) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.barcode = barcode;
        this.branch = branch;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
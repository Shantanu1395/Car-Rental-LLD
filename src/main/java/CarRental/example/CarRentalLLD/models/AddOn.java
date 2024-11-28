package CarRental.example.CarRentalLLD.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class AddOn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String description;
    private double price;

    @ManyToMany(mappedBy = "addOns")
    private List<Booking> bookings;

    // Getters and Setters
}
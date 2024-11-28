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

    public AddOn() {
    }

    public AddOn(String name, String type, String description, double price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    // Getters and Setters

    public double getPrice() {
        return price;
    }
}
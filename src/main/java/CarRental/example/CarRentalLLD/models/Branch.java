package CarRental.example.CarRentalLLD.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String address;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    public Branch() {
    }

    public Branch(String name, String location, String address) {
        this.name = name;
        this.location = location;
        this.address = address;
    }

    // Getters and Setters
}

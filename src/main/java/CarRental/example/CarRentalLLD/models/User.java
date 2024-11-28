package CarRental.example.CarRentalLLD.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String contactDetails;
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getEmail(){
        return this.email;
    }
}

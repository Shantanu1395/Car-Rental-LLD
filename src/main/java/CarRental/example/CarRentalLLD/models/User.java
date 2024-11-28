package CarRental.example.CarRentalLLD.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
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

    public User() {
    }

    public User(String username, String email, String password, String contactDetails, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.contactDetails = contactDetails;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword() {
        return password;
    }
}

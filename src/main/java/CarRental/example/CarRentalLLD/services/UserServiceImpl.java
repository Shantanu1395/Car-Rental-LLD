package CarRental.example.CarRentalLLD.services;

import CarRental.example.CarRentalLLD.models.User;
import CarRental.example.CarRentalLLD.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String username, String email, String password, String contactDetails, String role) {
        User user = new User(username, email, password, contactDetails, role);
        return userRepository.save(user);
    }

    @Override
    public String loginUser(String email, String password) {
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return "Login successful";
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}

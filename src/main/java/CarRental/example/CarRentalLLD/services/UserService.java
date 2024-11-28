package CarRental.example.CarRentalLLD.services;

import CarRental.example.CarRentalLLD.models.User;

public interface UserService {
    User registerUser(String username, String email, String password, String contactDetails, String role);
    String loginUser(String username, String password);
    User getUserById(Long userId);
}

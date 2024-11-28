package CarRental.example.CarRentalLLD.controllers;

import CarRental.example.CarRentalLLD.dtos.LoginDTO;
import CarRental.example.CarRentalLLD.dtos.UserDTO;
import CarRental.example.CarRentalLLD.models.User;
import CarRental.example.CarRentalLLD.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. Create a New User
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User user = userService.registerUser(
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getContactDetails(),
                userDTO.getRole()
        );
        return ResponseEntity.ok(user);
    }

    // 2. Login User
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        String token = userService.loginUser(loginDTO.getUsername(), loginDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    // 3. Get User Details
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}
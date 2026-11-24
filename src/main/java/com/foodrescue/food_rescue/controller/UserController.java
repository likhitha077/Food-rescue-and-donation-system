
package com.foodrescue.food_rescue.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.foodrescue.food_rescue.model.User;
import com.foodrescue.food_rescue.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    @PostMapping("/register")
    public int registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Find user using email
    @GetMapping("/email/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Login user
    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody Map<String, String> loginData) {

        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = userService.findUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            return Map.of(
                "success", true,
                "message", "Login successful",
                "userId", user.getUserId(),
                "name", user.getName(),
                "role", user.getRole()
            );
        }

        return Map.of(
            "success", false,
            "message", "Invalid email or password"
        );
    }
}



package com.foodrescue.food_rescue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodrescue.food_rescue.dao.UserDAO;
import com.foodrescue.food_rescue.model.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    // Constructor injection
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Register a new user
    @Override
    public int registerUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException(
                    "User details cannot be empty");
        }

        if (user.getName() == null ||
                user.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Name is required");
        }

        if (user.getEmail() == null ||
                user.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "Email is required");
        }

        if (user.getPassword() == null ||
                user.getPassword().isBlank()) {
            throw new IllegalArgumentException(
                    "Password is required");
        }

        if (user.getRole() == null ||
                !(user.getRole().equals("DONOR") ||
                  user.getRole().equals("RECEIVER") ||
                  user.getRole().equals("ADMIN"))) {

            throw new IllegalArgumentException(
                    "Invalid user role");
        }

        // Check whether email already exists
        User existingUser =
                userDAO.findUserByEmail(user.getEmail());

        if (existingUser != null) {
            throw new IllegalArgumentException(
                    "Email is already registered");
        }

        // Save user in database
        return userDAO.registerUser(user);
    }

    // Find user by email
    @Override
    public User findUserByEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "Email cannot be empty");
        }

        return userDAO.findUserByEmail(email);
    }

    // Get all users
    @Override
    public List<User> getAllUsers() {

        return userDAO.getAllUsers();
    }
}


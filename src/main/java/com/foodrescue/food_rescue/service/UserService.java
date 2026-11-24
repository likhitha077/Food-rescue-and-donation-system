
package com.foodrescue.food_rescue.service;

import java.util.List;
import com.foodrescue.food_rescue.model.User;

public interface UserService {

    // Register a new user
    int registerUser(User user);

    // Find user using email
    User findUserByEmail(String email);

    // Get all registered users
    List<User> getAllUsers();
}

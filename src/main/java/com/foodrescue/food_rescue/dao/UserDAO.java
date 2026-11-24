
package com.foodrescue.food_rescue.dao;

import java.util.List;
import com.foodrescue.food_rescue.model.User;

public interface UserDAO {

    // Register a new user
    int registerUser(User user);

    // Find a user using email
    User findUserByEmail(String email);

    // Get all registered users
    List<User> getAllUsers();

}

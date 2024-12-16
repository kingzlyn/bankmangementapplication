package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public boolean registerUser(User user) {
        // Check if user already exists (based on username and role)
        if (userRepository.findByUsernameAndRole(user.getUsername(), user.getRole()) != null) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    // Validate user for login
    public boolean validateUser(User user) {
        // Find user by username and role
        User existingUser = userRepository.findByUsernameAndRole(user.getUsername(), user.getRole());
        return existingUser != null && existingUser.getPassword().equals(user.getPassword());
    }

    public User save(User user) {
        // Perform any necessary validation or transformations on the user object
        // For example: check if the user already exists, validate input fields, etc.

        return userRepository.save(user); // Save the user entity to the database
    }
}



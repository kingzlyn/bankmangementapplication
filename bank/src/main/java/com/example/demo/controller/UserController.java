package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        userService.save(user);
        // Create a JSON response with a success message
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User registered successfully"));
    }





    // Login user
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean isValidUser = userService.validateUser(user);
        if (isValidUser) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.badRequest().body("Invalid username, password, or role!");
        }
    }
}


 class ApiResponse {
    private String message;

    // Constructor
    public ApiResponse(String message) {
        this.message = message;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


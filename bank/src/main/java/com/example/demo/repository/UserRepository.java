package com.example.demo.repository;




import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find a user by username and role
    User findByUsernameAndRole(String username, String role);

   
}


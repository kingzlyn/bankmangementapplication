package com.example.demo.controller;




import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private TransactionService transactionService;
    
    String photoFileName;

    @RequestMapping("/nextAccountNumber")
    public String getNextAccountNumber(HttpSession session) {
        String lastAccountNumber = customerService.getLastAccountNumber();
        int nextAccountNumber = Integer.parseInt(lastAccountNumber) + 1; // Increment the account number
        session.setAttribute("nextAccountNumber", String.valueOf(nextAccountNumber));
        return String.valueOf(nextAccountNumber);
    }
    // Define the path to store images (e.g., in 'uploads' directory)
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads";

    // Endpoint to create a customer account
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestParam("photo") MultipartFile photo,
                                                 @RequestParam("sign") MultipartFile sign,
                                                 @RequestParam String name,
                                                 @RequestParam int age,
                                                 @RequestParam String accountNumber,
                                                 @RequestParam String gender,
                                                 @RequestParam String mobile,
                                                 @RequestParam String aadhar,
                                                 @RequestParam String pan,
                                                 @RequestParam String ifsc,
                                                 @RequestParam String password) throws IOException {

        // Generate unique file names for the photo and signature
        String photoFileName =photo.getOriginalFilename();
        String signFileName = sign.getOriginalFilename();

        // Save the uploaded files to the server's storage
        Path photoPath = Path.of(UPLOAD_DIR, photoFileName);
        Path signPath = Path.of(UPLOAD_DIR, signFileName);
        Files.createDirectories(photoPath.getParent()); // Create directories if they don't exist
        photo.transferTo(photoPath);
        sign.transferTo(signPath);

        
//        String photoPathStr = photoPath.toString().replace("\\", "/");
//        String signPathStr = signPath.toString().replace("\\", "/");
        // Create a new Customer object
        String photoPathStr = photoFileName.toString();
      String signPathStr = signFileName.toString();
        
        
        Customer customer = new Customer();
        customer.setAccountNumber(accountNumber); // Example account number
        customer.setName(name);
        customer.setAge(age);
        customer.setGender(gender);
        customer.setMobile(mobile);
        customer.setAadhar(aadhar);
        customer.setPan(pan);
        customer.setIfsc(ifsc);
        customer.setBalance(0);
        customer.setPassword(password);
        customer.setPhoto("http://localhost:8080/uploads/"+photoPathStr); // Save the file path (URL) of the photo
        customer.setSignature("http://localhost:8080/uploads/"+signPathStr);   // Save the file path (URL) of the signature

        // Save the customer to the database
        customerService.createCustomer(customer);

        return ResponseEntity.ok("Account created successfully!");
    }

    @GetMapping("/search/{accountNumber}")
    public ResponseEntity<Customer> searchCustomer(@PathVariable String accountNumber) {
        Customer customer = customerService.getCustomerByAccountNumber(accountNumber);
        if (customer != null) {
            // Return the customer along with the relative path for the photo
            String photoUrl =customer.getPhoto(); // Assuming the photo is saved in /uploads/
            customer.setPhoto(photoUrl); // Set the photo URL as a relative path
            
            
            String signUrl =customer.getSignature(); // Assuming the photo is saved in /uploads/
            customer.setSignature(signUrl);
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> request) {
        String accountNumber = request.get("accountNumber");
        String password = request.get("password");

        String message = customerService.registerCustomer(accountNumber, password);

        Map<String, String> response = new HashMap<>();
        response.put("message", message);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String accountNumber = request.get("accountNumber");
        String password = request.get("password");

        boolean success = customerService.loginCustomer(accountNumber, password);

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);

        if (success) {
            response.put("message", "Login successful!");
        } else {
            response.put("message", "Invalid account number or password.");
        }

        return ResponseEntity.ok(response);
    }
    
    
    @GetMapping("/{accountNumber}/balance")
    public Map<String, Object> getBalance(@PathVariable String accountNumber) {
        return transactionService.getBalance(accountNumber);
    }

}


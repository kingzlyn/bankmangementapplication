package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private int accountCounter = 1001; // Start account number from 1001

    // Create a new customer and assign an account number
    public Customer createCustomer(Customer customer) {
        customer.setAccountNumber(String.valueOf(accountCounter));
        accountCounter++;
        return customerRepository.save(customer);
    }

    // Retrieve a customer by account number
    public Customer getCustomerByAccountNumber(String accountNumber) {
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer == null) {
            throw new RuntimeException("Customer with account number " + accountNumber + " not found.");
        }
        return customer;
    }

    // Retrieve all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get the last assigned account number
    public String getLastAccountNumber() {
        Customer lastCustomer = customerRepository.findTopByOrderByIdDesc();
        return lastCustomer != null ? lastCustomer.getAccountNumber() : "1000"; // Default to 1000 if no customers exist
    }

    // Register a new customer with an account number and password
    public String registerCustomer(String accountNumber, String password) {
        Customer existingCustomer = customerRepository.findByAccountNumber(accountNumber);

        if (existingCustomer != null) {
            return "Account already exists!";
        }

        Customer newCustomer = new Customer();
        newCustomer.setAccountNumber(accountNumber);
        newCustomer.setPassword(password);
        customerRepository.save(newCustomer);

        return "Registration successful!";
    }

    // Authenticate a customer by account number and password
    public boolean loginCustomer(String accountNumber, String password) {
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer == null) {
            throw new RuntimeException("Invalid account number.");
        }
        return customer.getPassword().equals(password);
    }
}

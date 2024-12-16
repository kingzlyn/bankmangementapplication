package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Transaction;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Deposit method
    public Map<String, String> deposit(String accountNumber, int amount) {
        // Find the customer using the account number
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer == null) {
            throw new RuntimeException("Account not found");
        }
        
        // Update the balance
        customer.setBalance(customer.getBalance() + amount);
        customerRepository.save(customer);

        // Save the deposit transaction
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionType("Deposit");
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        // Return success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "Deposit successful");
        return response;
    }

    // Withdraw method
    public Map<String, String> withdraw(String accountNumber, int amount) {
        // Find the customer using the account number
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer == null) {
            throw new RuntimeException("Account not found");
        }

        // Check for sufficient balance
        if (customer.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Update the balance
        customer.setBalance(customer.getBalance() - amount);
        customerRepository.save(customer);

        // Save the withdrawal transaction
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionType("Withdrawl");
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        // Return success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "Withdrawal successful");
        return response;
    }

    // Get balance method
    public Map<String, Object> getBalance(String accountNumber) {
        // Find the customer using the account number
        Customer customer = customerRepository.findByAccountNumber(accountNumber);
        if (customer == null) {
            throw new RuntimeException("Account not found");
        }

        // Return the balance in a map
        Map<String, Object> response = new HashMap<>();
        response.put("balance", customer.getBalance());
        return response;
    }

    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
       
    }
    
    
    public List<Transaction> getTransactionsByDate(String date) {
        LocalDateTime startOfDay = LocalDateTime.parse(date + "T00:00:00");
        LocalDateTime endOfDay = LocalDateTime.parse(date + "T23:59:59");

        return transactionRepository.findByDateBetween(startOfDay, endOfDay);
    }

}

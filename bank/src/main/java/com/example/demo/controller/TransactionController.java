package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
  
    
    @PostMapping("/deposit")
    public ResponseEntity<Map<String, String>> deposit(@RequestBody Transaction transaction) {
        // Now the transaction will be automatically mapped from the incoming JSON
        transactionService.deposit(transaction.getAccountNumber(),transaction.getAmount());  // Pass the transaction to your service
        Map<String, String> response = new HashMap<>();
        response.put("message", "Deposit successful");
        
        return ResponseEntity.ok(response);
    }


    @PostMapping("/withdraw")
    public ResponseEntity<Map<String, String>> withdraw(@RequestBody Transaction transaction) {
        
    	transactionService.withdraw(transaction.getAccountNumber(),transaction.getAmount());
        // Perform withdrawal and save the transaction
    	 Map<String, String> response = new HashMap<>();
         response.put("message", "withdraw successful");
         
         return ResponseEntity.ok(response);
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<?> checkBalance(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getBalance(accountNumber));
    }
    
    
    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactions);
    }
    
    
    @GetMapping("/daily/{date}")
    public ResponseEntity<Map<String, Integer>> getDailyTransactions(@PathVariable String date) {
        List<Transaction> transactions = transactionService.getTransactionsByDate(date);

        // Filter and calculate totals
        int totalDeposits = transactions.stream()
                .filter(txn -> txn.getTransactionType().equalsIgnoreCase("Deposit"))
                .mapToInt(Transaction::getAmount)
                .sum();

        int totalWithdrawals = transactions.stream()
                .filter(txn -> txn.getTransactionType().equalsIgnoreCase("Withdrawl"))
                .mapToInt(Transaction::getAmount)
                .sum();

        // Prepare the response
        Map<String, Integer> totals = new HashMap<>();
        totals.put("Deposits", totalDeposits);
        totals.put("Withdrawals", totalWithdrawals);

        return ResponseEntity.ok(totals);
    }

}

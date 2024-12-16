package com.example.demo.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Loan;
import com.example.demo.service.LoanService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<?> createLoan(
            @RequestParam(required = true) String accountNumber,
            @RequestParam(required = true) String loanType,
            @RequestParam(required = true) Double loanAmount,
            @RequestParam(required = true) String status) {

        // Basic validation
        if (accountNumber.isEmpty() || loanType.isEmpty() || loanAmount <= 0 || status.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid input parameters.");
        }

        System.out.println("Loan application received for account: " + accountNumber);
        Loan loan = loanService.createLoan(accountNumber, loanType, loanAmount, status);
        return ResponseEntity.ok(loan);
    }

    
    // Fetch loans with status "Pending"
    @GetMapping("/pending")
    public ResponseEntity<List<Loan>> getPendingLoans() {
        List<Loan> pendingLoans = loanService.getLoansByStatus("Pending");
        return ResponseEntity.ok(pendingLoans);
    }

    @PostMapping("/update-status-by-account")
    public ResponseEntity<?> updateLoanStatusByAccount(@RequestBody Map<String, Object> request) {
        String loanId = (String) request.get("loanId");
        String newStatus = (String) request.get("newStatus");

        if (loanId == null || newStatus == null) {
            return ResponseEntity.badRequest().body("Invalid loanId or newStatus.");
        }

        Optional<Loan> loan = loanService.updateLoanStatusByAccountNumber(loanId, newStatus);

        if (loan.isPresent()) {
            return ResponseEntity.ok(loan.get());
        } else {
            return ResponseEntity.status(404).body("Loan not found.");
        }
    }

   
}


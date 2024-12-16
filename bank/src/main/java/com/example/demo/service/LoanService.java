package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Loan;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.LoanRepository;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public LoanService(LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    public Loan createLoan(String accountNumber, String loanType, Double loanAmount, String status) {
//        Customer customer = customerRepository.findByAccountNumber(accountNumber);
//        if (customer == null) {
//            throw new RuntimeException("Customer with account number " + accountNumber + " not found.");
//        }

        Loan loan = new Loan();
        loan.setAccountnumber(accountNumber);;
        loan.setLoanType(loanType);
        loan.setLoanAmount(loanAmount);
        loan.setStatus(status);

        return loanRepository.save(loan);
    }
    
    
 // Fetch loans by status
    public List<Loan> getLoansByStatus(String status) {
        return loanRepository.findByStatus(status);
    }

    public Optional<Loan> updateLoanStatusByAccountNumber(String accountNumber, String newStatus) {
    	 int accountNumberint = Integer.parseInt(accountNumber);
        Optional<Loan> loan = loanRepository.findById(accountNumberint);
        if (loan.isPresent()) {
            Loan loanToUpdate = loan.get();
            loanToUpdate.setStatus(newStatus);
            loanRepository.save(loanToUpdate);
            return Optional.of(loanToUpdate);
        }
        return Optional.empty();
    }
    
   
}

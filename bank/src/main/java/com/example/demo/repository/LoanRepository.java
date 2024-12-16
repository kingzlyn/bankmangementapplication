package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	List<Loan> findByStatus(String status);

	Optional<Loan> findById(int accountNumber);
	
	


	
}


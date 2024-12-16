package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Transaction;



@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	public Transaction save(com.example.demo.model.Transaction transaction);

	List<Transaction> findByAccountNumber(String accountNumber);

	 List<Transaction> findByDateBetween(LocalDateTime start, LocalDateTime end);
}

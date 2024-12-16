package com.example.demo.model;



import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")  // Assuming the table name is 'transactions'
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String transactionType; // Can be "Deposit" or "Withdrawal"
    private int amount;
    private LocalDateTime date;
    

    // Constructors
    public Transaction() {}

    public Transaction(String accountNumber, String transactionType, int amount) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                '}';
    }

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}


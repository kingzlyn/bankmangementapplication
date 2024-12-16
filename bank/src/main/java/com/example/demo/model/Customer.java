package com.example.demo.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false, unique = true)
    private String accountNumber;
    private String name;
    private int age;
    private String gender;
    private String mobile;
    private String aadhar;
    private String pan;
    private String ifsc;
    private String photo; // File path for the photo
    private String signature; // File path for the signature
  private String password;
    
    private int balance;
    // Getters and Setters
    public Customer() {
    
    }
    
    
    public Customer(String accountNumber, String password) {
        this.accountNumber = accountNumber;
        this.password = password;
    }
    public Customer(Long id, String accountNumber, String name, int age, String gender, String mobile, String aadhar,
			String pan, String ifsc, String photo, String signature, int balance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobile = mobile;
		this.aadhar = aadhar;
		this.pan = pan;
		this.ifsc = ifsc;
		this.photo = photo;
		this.signature = signature;
		this.balance = balance;
	}
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

package com.example.demo.model;



import jakarta.persistence.*;

@Entity
@Table(name = "daily_jewel")
public class DailyJewel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double dailyJewelAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDailyJewelAmount() {
        return dailyJewelAmount;
    }

    public void setDailyJewelAmount(Double dailyJewelAmount) {
        this.dailyJewelAmount = dailyJewelAmount;
    }
}


package com.example.demo.repository;


import com.example.demo.model.DailyJewel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyJewelRepository extends JpaRepository<DailyJewel, Long> {
}

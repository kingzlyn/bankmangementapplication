package com.example.demo.controller;



import com.example.demo.service.DailyJewelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class DailyJewelController {

    @Autowired
    private DailyJewelService dailyJewelService;

    // Endpoint to Save or Update Daily Jewel Amount
    @PostMapping("/update-daily-jewel")
    public ResponseEntity<String> updateDailyJewelAmount(@RequestBody Map<String, Double> request) {
        Double dailyJewelAmount = request.get("dailyJewelAmount");
        String responseMessage = dailyJewelService.saveOrUpdateDailyJewelAmount(dailyJewelAmount);
        return ResponseEntity.ok(responseMessage);
    }

    // Endpoint to Retrieve Daily Jewel Amount
    @GetMapping("/get-daily-jewel")
    public ResponseEntity<Double> getDailyJewelAmount() {
        Double dailyJewelAmount = dailyJewelService.getDailyJewelAmount();
        return ResponseEntity.ok(dailyJewelAmount);
    }
}


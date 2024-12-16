package com.example.demo.service;



import com.example.demo.model.DailyJewel;
import com.example.demo.repository.DailyJewelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DailyJewelService {

    @Autowired
    private DailyJewelRepository dailyJewelRepository;

    // Save or Update Daily Jewel Amount
    public String saveOrUpdateDailyJewelAmount(Double dailyJewelAmount) {
        // Always update the first record (or create it if none exists)
        DailyJewel dailyJewel = dailyJewelRepository.findById(1L).orElse(new DailyJewel());
        dailyJewel.setDailyJewelAmount(dailyJewelAmount);
        dailyJewelRepository.save(dailyJewel);
        return "Daily Jewel Amount saved/updated successfully!";
    }

    // Retrieve Daily Jewel Amount
    public Double getDailyJewelAmount() {
        Optional<DailyJewel> result = dailyJewelRepository.findById(1L);
        return result.map(DailyJewel::getDailyJewelAmount).orElse(0.0); // Return 0.0 if not present
    }
}

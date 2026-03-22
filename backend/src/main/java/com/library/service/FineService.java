package com.library.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class FineService {

    public double calculate(LocalDate dueDate, LocalDate returnDate) {

        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);

        // ✅ 14 days er modhhe return hole fine 0
        if (daysLate <= 0) {
            return 0;
        }

        // 🔥 14 din por ₹10 per day
        return daysLate * 10;
    }
}
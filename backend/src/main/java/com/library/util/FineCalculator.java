package com.library.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    private FineCalculator() {}

    public static double calculateFine(LocalDate dueDate, LocalDate returnDate, double finePerDay) {
        if (dueDate == null || returnDate == null) return 0.0;
        if (!returnDate.isAfter(dueDate)) return 0.0;
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        return Math.max(0, daysLate) * Math.max(0, finePerDay);
    }
}


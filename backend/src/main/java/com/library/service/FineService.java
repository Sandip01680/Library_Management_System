package com.library.service;

import com.library.util.FineCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FineService {

    private final double finePerDay;

    public FineService(@Value("${library.fine.per-day:5.0}") double finePerDay) {
        this.finePerDay = finePerDay;
    }

    public double calculate(LocalDate dueDate, LocalDate returnDate) {
        return FineCalculator.calculateFine(dueDate, returnDate, finePerDay);
    }
}


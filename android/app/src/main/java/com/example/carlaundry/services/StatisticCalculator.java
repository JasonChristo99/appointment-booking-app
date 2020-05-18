package com.example.carlaundry.services;

import java.time.LocalDateTime;

public interface StatisticCalculator {
    double calculate(LocalDateTime startDate, LocalDateTime endDate);
}

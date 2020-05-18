package com.example.carlaundry.services;

import java.time.LocalDateTime;

public class StatisticsCalculatorService {
    StatisticCalculator statisticCalculator;

    public StatisticsCalculatorService(StatisticCalculator statisticCalculator) {
        this.statisticCalculator = statisticCalculator;
    }

    public double calculateStatistic(LocalDateTime startDate, LocalDateTime endDate) {
        return statisticCalculator.calculate(startDate, endDate);
    }
}

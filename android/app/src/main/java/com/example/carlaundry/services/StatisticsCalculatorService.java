package com.example.carlaundry.services;

import java.time.LocalDateTime;

public class StatisticsCalculatorService {
    public static String[] statisticTypes = {"Ποσοστό ακυρώσεων", "Συνολικά ραντεβού", "Συνολικά έσοδα"};

    private StatisticCalculator statisticCalculator;

    public StatisticsCalculatorService(StatisticCalculator statisticCalculator) {
        this.statisticCalculator = statisticCalculator;
    }

    public double calculateStatistic(LocalDateTime startDate, LocalDateTime endDate) {
        return statisticCalculator.calculate(startDate, endDate);
    }
}

package com.example.carlaundry.services;

import java.time.LocalDateTime;

public class StatisticsCalculatorService {
    public static String[] statisticTypes = {"Συνολικά έσοδα", "Συνολικά ραντεβού", "Ποσοστό ακυρώσεων"};

    private StatisticCalculator statisticCalculator;

    public StatisticsCalculatorService(StatisticCalculator statisticCalculator) {
        this.statisticCalculator = statisticCalculator;
    }

    /**
     * Given a statistic type specific calculator object, a start and an end date,
     * this method calculates the requested metric and returns the result to the user.
     * @param startDate the start date of the statistic calculation
     * @param endDate the end date of the statistic calculation
     * @return the result value of the requested statistic
     */
    public double calculateStatistic(LocalDateTime startDate, LocalDateTime endDate) {
        return statisticCalculator.calculate(startDate, endDate);
    }
}

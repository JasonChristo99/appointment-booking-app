package com.example.carlaundry.view.Statistics;

import com.example.carlaundry.services.CancelRateCalculator;
import com.example.carlaundry.services.StatisticsCalculatorService;
import com.example.carlaundry.services.TotalAppointmentsCalculator;
import com.example.carlaundry.services.TotalSalesCalculator;

import java.time.LocalDateTime;

public class StatisticsPresenter {
    private StatisticsView statisticsView;

    public StatisticsPresenter(StatisticsView statisticsView) {
        this.statisticsView = statisticsView;
    }

    public void calculate(LocalDateTime start, LocalDateTime end, int spinnerPosition) {
        double result = -1;
        switch (spinnerPosition) {
            case 0:
                result = new StatisticsCalculatorService(new TotalSalesCalculator()).calculateStatistic(start, end);
                break;
            case 1:
                result = new StatisticsCalculatorService(new TotalAppointmentsCalculator()).calculateStatistic(start, end);
                break;
            case 2:
                result = new StatisticsCalculatorService(new CancelRateCalculator()).calculateStatistic(start, end);
                break;
        }
        statisticsView.showMessage(StatisticsCalculatorService.statisticTypes[spinnerPosition] + ": " + result);
    }
}

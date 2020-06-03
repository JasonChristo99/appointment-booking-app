package com.example.carlaundry.view.Statistics;

import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.services.StatisticsCalculatorService;
import com.example.carlaundry.view.Login.LoginPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class StatisticsPresenterTest {
    private StatisticsPresenter presenter;
    private StatisticsViewStub view;

    @Before
    public void setUp() {
        view = new StatisticsViewStub();
        presenter = new StatisticsPresenter(view);
    }

    /**
     * Tests the case of total sales calculation.
     */
    @Test
    public void totalSalesCalculation() {
        int statType = 0;
        presenter.calculate(
                LocalDateTime.of(
                        Initializer.getDummyDate(),
                        LocalTime.of(8, 0)
                ),
                LocalDateTime.of(
                        Initializer.getDummyDate(),
                        LocalTime.of(16, 0)
                ),
                statType
        );

        Assert.assertTrue(view.getLog().contains(StatisticsCalculatorService.statisticTypes[statType]));
    }

    /**
     * Tests the case of total appointments calculation.
     */
    @Test
    public void totalAppointmentsCalculation() {
        int statType = 1;
        presenter.calculate(
                LocalDateTime.of(
                        Initializer.getDummyDate(),
                        LocalTime.of(8, 0)
                ),
                LocalDateTime.of(
                        Initializer.getDummyDate(),
                        LocalTime.of(16, 0)
                ),
                statType
        );

        Assert.assertTrue(view.getLog().contains(StatisticsCalculatorService.statisticTypes[statType]));
    }

    /**
     * Tests the case of cancel rate calculation.
     */
    @Test
    public void cancelRateCalculation() {
        int statType = 2;
        presenter.calculate(
                LocalDateTime.of(
                        Initializer.getDummyDate(),
                        LocalTime.of(8, 0)
                ),
                LocalDateTime.of(
                        Initializer.getDummyDate(),
                        LocalTime.of(16, 0)
                ),
                statType
        );

        Assert.assertTrue(view.getLog().contains(StatisticsCalculatorService.statisticTypes[statType]));
    }
}

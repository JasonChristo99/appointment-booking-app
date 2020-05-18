package com.example.carlaundry.domain;

import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.services.CancelRateCalculator;
import com.example.carlaundry.services.StatisticsCalculatorService;
import com.example.carlaundry.services.TotalAppointmentsCalculator;
import com.example.carlaundry.services.TotalSalesCalculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDateTime;

public class StatisticsCalculationTest {

    @Before
    public void setup() {
        Initializer.resetAll();
    }

    @Test
    public void calculateTotalAppointmentsComplete() {
        // create 5 appointments
        for (int i = 0; i < 5; i++) {
            Appointment appointment = new Appointment(
                    1,
                    LocalDateTime.of(2020, 1, 1, 10 + i, 0),
                    Initializer.getDummyCustomer(),
                    Initializer.getDummyCleaningStuffMember(),
                    Initializer.getDummyCleaningType(),
                    Initializer.getDummyCar());
            appointment.schedule();
            appointment.complete();
        }

        // calculate statistics for above appointments
        StatisticsCalculatorService statisticsCalculatorService = new StatisticsCalculatorService(new TotalAppointmentsCalculator());
        double statValue = statisticsCalculatorService.calculateStatistic(
                LocalDateTime.of(2020, 1, 1, 8, 0),
                LocalDateTime.of(2020, 1, 1, 23, 0));

        Assert.assertEquals(statValue, 5, 0.00001);
    }

    @Test
    public void calculateTotalSales() {
        // create 5 dummy appointments
        for (int i = 0; i < 5; i++) {
            Appointment appointment = new Appointment(
                    1,
                    LocalDateTime.of(2020, 1, 1, 10 + i, 0),
                    Initializer.getDummyCustomer(),
                    Initializer.getDummyCleaningStuffMember(),
                    Initializer.getDummyCleaningType(), // dummy cleaning type has a value of 10
                    Initializer.getDummyCar());
            appointment.schedule();
            appointment.complete();
        }

        // calculate statistic for above appointments
        StatisticsCalculatorService statisticsCalculatorService = new StatisticsCalculatorService(new TotalSalesCalculator());
        double statValue = statisticsCalculatorService.calculateStatistic(
                LocalDateTime.of(2020, 1, 1, 8, 0),
                LocalDateTime.of(2020, 1, 1, 23, 0));

        Assert.assertEquals(statValue, 50, 0.00001);

    }

    @Test
    public void calculateCancelRate() {
        // create 3 appointments of type complete
        for (int i = 0; i < 3; i++) {
            Appointment appointment = new Appointment(
                    1,
                    LocalDateTime.of(2020, 1, 1, 10 + i, 0),
                    Initializer.getDummyCustomer(),
                    Initializer.getDummyCleaningStuffMember(),
                    Initializer.getDummyCleaningType(), // dummy cleaning type has a value of 10
                    Initializer.getDummyCar());
            appointment.schedule();
            appointment.complete();
        }

        // create 3 appointments of type cancelled
        for (int i = 0; i < 3; i++) {
            Appointment appointment = new Appointment(
                    1,
                    LocalDateTime.of(2020, 1, 1, 13 + i, 0),
                    Initializer.getDummyCustomer(),
                    Initializer.getDummyCleaningStuffMember(),
                    Initializer.getDummyCleaningType(), // dummy cleaning type has a value of 10
                    Initializer.getDummyCar());
            appointment.schedule();
            appointment.setAptState(AppointmentState.CANCELED);
        }

        // calculate statistic for above appointments
        StatisticsCalculatorService statisticsCalculatorService = new StatisticsCalculatorService(new CancelRateCalculator());
        double statValue = statisticsCalculatorService.calculateStatistic(
                LocalDateTime.of(2020, 1, 1, 8, 0),
                LocalDateTime.of(2020, 1, 1, 23, 0));

        Assert.assertEquals(statValue, 0.5, 0.00001);

    }
}

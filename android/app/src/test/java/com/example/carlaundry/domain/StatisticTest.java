package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.util.StatisticType;
import com.example.carlaundry.domain.Statistic;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDateTime;

public class StatisticTest {

    @Before
    public void setup() {
        Initializer.resetAll();
    }

    @Test
    public void calculateTotalAppointmentsComplete() {
        //create some statistics of type 'total_appointments_complete to calculate

        Statistic stat1date = new Statistic(StatisticType.TOTAL_APPOINTMENTS_COMPLETE,
                LocalDateTime.of(2020, 1, 1, 8, 0),
                LocalDateTime.of(2020, 1, 1, 23, 0));
        Statistic stat1value = new Statistic(StatisticType.TOTAL_APPOINTMENTS_COMPLETE, 10);

        //create 5 dummy appointments
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
        Assert.assertEquals((int) stat1value.calculate(), 5);
    }

    @Test
    public void calculateTotalSales() {
        //create some statistics of type 'total_sales' to calculate

        Statistic stat1date = new Statistic(StatisticType.TOTAL_SALES,
                LocalDateTime.of(2020, 1, 1, 8, 0),
                LocalDateTime.of(2020, 1, 1, 23, 0));
        Statistic stat1value = new Statistic(StatisticType.TOTAL_SALES, 10);

        //create 5 dummy appointments
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
        Assert.assertSame((int) stat1value.calculate(), 50);

    }

    @Test
    public void calculateCancelRate() {
        //create some statistics
        Statistic stat1date = new Statistic(StatisticType.CANCEL_RATE,
                LocalDateTime.of(2020, 1, 1, 8, 0),
                LocalDateTime.of(2020, 1, 1, 23, 0));


        //create 3 dummy appointments of type complete
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

        //create 3 dummy appointments of type cancelled
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

        Assert.assertEquals(stat1date.calculate(), 0.5, 0.0);

    }
}

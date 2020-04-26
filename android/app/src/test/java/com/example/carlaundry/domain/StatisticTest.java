package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.util.StatisticType;
import com.example.carlaundry.domain.Statistic;

import org.junit.Test;
import org.junit.Assert;
import java.time.LocalDateTime;

public class StatisticTest {

    @Test //TODO
    public void calculateTotalAppointmentsComplete() {
    //create some statistics of type appointment to calculate

      Statistic stat1date = new Statistic(StatisticType.TOTAL_APPOINTMENTS_COMPLETE,
              LocalDateTime.of(2020, 1, 1, 8, 0),
              LocalDateTime.of(2020, 1, 1, 23, 0));
      Statistic stat1value = new Statistic(StatisticType.TOTAL_APPOINTMENTS_COMPLETE,10);
      //create 10 dummy appointments
      for(int i=0; i<10; i++){
          Appointment appointment = new Appointment(
                  1,
                  LocalDateTime.of(2020, 1, 1, 10+i, 0),
                  Initializer.getDummyCustomer(),
                  Initializer.getDummyCleaningStuffMember(),
                  Initializer.getDummyCleaningType(),
                  Initializer.getDummyCar());
                  AppointmentsDAO.add(appointment);
                  appointment.complete();}
                  Assert.assertSame((int)stat1value.calculate(), 10);



    }

    @Test //TODO
    public void calculateTotalSales() {
        //create some statistics of type appointment to calculate

        Statistic stat1date = new Statistic(StatisticType.TOTAL_SALES,
                LocalDateTime.of(2020, 1, 1, 8, 0),
                LocalDateTime.of(2020, 1, 1, 23, 0));
        Statistic stat1value = new Statistic(StatisticType.TOTAL_SALES,10);

        //create 10 dummy appointments
        for(int i=0; i<10; i++){
            Appointment appointment = new Appointment(
                    1,
                    LocalDateTime.of(2020, 1, 1, 10+i, 0),
                    Initializer.getDummyCustomer(),
                    Initializer.getDummyCleaningStuffMember(),
                    Initializer.getDummyCleaningType(), // dummy cleaning type has a value of 10
                    Initializer.getDummyCar());
            AppointmentsDAO.add(appointment);
            appointment.complete();}
        Assert.assertSame((int)stat1value.calculate(), 100);

    }

    @Test //TODO
    public void calculateCancelRate() {
        //create some statistics
        Statistic stat1date = new Statistic(StatisticType.CANCEL_RATE,
                LocalDateTime.of(2020, 1, 1, 8, 0),
                LocalDateTime.of(2020, 1, 1, 23, 0));
        //Statistic stat1value = new Statistic(StatisticType.CANCEL_RATE,20);
        //create 10 dummy appointments of type complete
        for(int i=0; i<10; i++){
            Appointment appointment = new Appointment(
                    1,
                    LocalDateTime.of(2020, 1, 1, 10+i, 0),
                    Initializer.getDummyCustomer(),
                    Initializer.getDummyCleaningStuffMember(),
                    Initializer.getDummyCleaningType(), // dummy cleaning type has a value of 10
                    Initializer.getDummyCar());
            AppointmentsDAO.add(appointment);
            appointment.complete();}
        //create 10 dummy appointments of type cancelled
        for(int i=0; i<10; i++){
            Appointment appointment = new Appointment(
                    1,
                    LocalDateTime.of(2020, 1, 1, 10+i, 0),
                    Initializer.getDummyCustomer(),
                    Initializer.getDummyCleaningStuffMember(),
                    Initializer.getDummyCleaningType(), // dummy cleaning type has a value of 10
                    Initializer.getDummyCar());
            AppointmentsDAO.add(appointment);
            appointment.setAptState(AppointmentState.CANCELED);}

        Assert.assertEquals(stat1date.calculate(),  0.5, 0.0);

    }
}

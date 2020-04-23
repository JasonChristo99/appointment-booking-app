package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.Initializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class AppointmentTest {
    @Before
    public void setUp() {
        Initializer.resetAll();
    }

    @Test
    public void testScheduleForAvailableStuff() {
        // create available stuff
        CleaningStuffMember stuff = Initializer.getDummyCleaningStuffMember();
        // create an appointment
        Appointment appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                Initializer.getDummyCustomer(),
                stuff,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        boolean result = appointment.schedule();
        Assert.assertEquals(result, true);
    }

    @Test
    public void testScheduleForNonAvailableStuff() {
        // create available stuff
        CleaningStuffMember stuff = Initializer.getDummyCleaningStuffMember();
        // create an appointment
        Appointment appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                Initializer.getDummyCustomer(),
                stuff,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        boolean result = appointment.schedule();
        Assert.assertEquals(result, true);

        // create an overlapping appointment -same time and same stuff member
        appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 30),
                Initializer.getDummyCustomer(),
                stuff,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointment -must fail
        result = appointment.schedule();
        Assert.assertEquals(result, false);
    }

    @Test
    public void testScheduleForNonWorkingStuff() {
        // create available stuff
        CleaningStuffMember stuff = Initializer.getDummyCleaningStuffMember();
        // create an appointment
        Appointment appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 7, 0),
                Initializer.getDummyCustomer(),
                stuff,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        boolean result = appointment.schedule();
        Assert.assertEquals(result, false);
    }

}

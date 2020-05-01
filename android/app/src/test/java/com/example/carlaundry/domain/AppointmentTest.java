package com.example.carlaundry.domain;

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
    public void scheduleAppointmentWhichStuffIsAvailableOnDate() {
        // create available stuffMember
        CleaningStuffMember stuffMember = Initializer.getDummyCleaningStuffMember();
        // create an appointment
        Appointment appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                Initializer.getDummyCustomer(),
                stuffMember,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        boolean result = appointment.schedule();
        Assert.assertTrue(result);
    }

    @Test
    public void scheduleAppointmentWhichStuffIsNotAvailableOnDate() {
        // create available stuffMember
        CleaningStuffMember stuffMember = Initializer.getDummyCleaningStuffMember();
        // create an appointment
        Appointment appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                Initializer.getDummyCustomer(),
                stuffMember,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        boolean result = appointment.schedule();
        Assert.assertTrue(result);
        // create an overlapping appointment -same time and same stuffMember member
        appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 30),
                Initializer.getDummyCustomer(),
                stuffMember,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointment -must fail
        result = appointment.schedule();
        Assert.assertFalse(result);
    }

    @Test
    public void scheduleAppointmentWhichStuffIsNotWorkingOnDate() {
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
        Assert.assertFalse(result);
    }

    @Test
    public void cancelPendingAppointment() {
        // create available stuffMember
        CleaningStuffMember stuffMember = Initializer.getDummyCleaningStuffMember();
        // create a pending appointment
        Appointment appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                Initializer.getDummyCustomer(),
                stuffMember,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        boolean result = appointment.schedule();
        Assert.assertTrue(result);
        // test cancel
        Assert.assertEquals(appointment.getAptState(), AppointmentState.PENDING);
        result = appointment.cancel();
        Assert.assertTrue(result);
    }

    @Test
    public void cancelNonPendingAppointment() {
        // create available stuffMember
        CleaningStuffMember stuffMember = Initializer.getDummyCleaningStuffMember();
        // create a pending appointment
        Appointment appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                Initializer.getDummyCustomer(),
                stuffMember,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        boolean result = appointment.schedule();
        Assert.assertTrue(result);
        // complete appointment
        appointment.setAptState(AppointmentState.COMPLETE);
        // test cancel
        Assert.assertEquals(appointment.getAptState(), AppointmentState.COMPLETE);
        result = appointment.cancel();
        Assert.assertFalse(result);
    }

    @Test
    public void completePendingAppointment() {
        // create an appointment
        Appointment appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                Initializer.getDummyCustomer(),
                Initializer.getDummyCleaningStuffMember(),
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        boolean result = appointment.schedule();
        Assert.assertTrue(result);
        // assert pending
        Assert.assertEquals(appointment.getAptState(), AppointmentState.PENDING);
        // complete appointment
        result = appointment.complete();
        Assert.assertTrue(result);
    }

    @Test
    public void completeCanceledAppointment() {
        // create an appointment
        Appointment appointment = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 10, 0),
                Initializer.getDummyCustomer(),
                Initializer.getDummyCleaningStuffMember(),
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        boolean result = appointment.schedule();
        Assert.assertTrue(result);
        // set state to canceled
        appointment.setAptState(AppointmentState.CANCELED);
        Assert.assertEquals(appointment.getAptState(), AppointmentState.CANCELED);
        // complete appointment
        result = appointment.complete();
        Assert.assertFalse(result);
    }

}

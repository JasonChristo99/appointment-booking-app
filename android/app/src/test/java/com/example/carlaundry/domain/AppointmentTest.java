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

    /**
     * Tests the appointment scheduling operation,
     * in the case when a cleaning stuff member is available
     * on the requested date.
     */
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

    /**
     * Tests the case of scheduling many appointments for the same
     * cleaning stuff member, on the same date, one after another.
     */
    @Test
    public void scheduleConsecutiveAppointmentsInSameDay() {
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
        // schedule appointment
        boolean result = appointment.schedule();
        Assert.assertTrue(result);

        // create an appointment
        Appointment appointment2 = new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 11, 0),
                Initializer.getDummyCustomer(),
                stuffMember,
                Initializer.getDummyCleaningType(),
                Initializer.getDummyCar()
        );
        // schedule appointemnt
        result = appointment2.schedule();
        Assert.assertTrue(result);

        System.out.println(AppointmentsDAO.getAppointments());
    }

    /**
     * Tests the case of scheduling an appointments,
     * in the case when the specified cleaner is not available
     * on the requested date, and so the schedule operation
     * should return false.
     */
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

    /**
     * Tests the case in which the specified cleaner of the appointment
     * does not work on the requested date. The schedule operation should return false.
     */
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

    /**
     * Tests the cancel operation on a scheduled appointment.
     */
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

    /**
     * Tests the canceling of a complete appointment, which should be prohibited.
     */
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
        // schedule appointment
        boolean result = appointment.schedule();
        Assert.assertTrue(result);
        // complete appointment
        appointment.setAptState(AppointmentState.COMPLETE);
        // test cancel
        Assert.assertEquals(appointment.getAptState(), AppointmentState.COMPLETE);
        result = appointment.cancel();
        Assert.assertFalse(result);
    }

    /**
     * Tests the completion operation of a pending appointment.
     */
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

    /**
     * Tests the canceling operation on an already completed appointment,
     * which should be prohibited.
     */
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

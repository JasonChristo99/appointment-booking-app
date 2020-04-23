package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.Initializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppointmentTest {
    @Before
    public void setUp() {
        Initializer.resetAll();
    }

    @Test
    public void testAddNonExistingAppointmentToCollection() {
        Appointment appointment = new Appointment();
        boolean result = appointment.addToCollection();
        Assert.assertEquals(result, true);
        Assert.assertEquals(AppointmentsDAO.getAppointments().size(), 1);
        Assert.assertSame(AppointmentsDAO.find(appointment.getAptId()), appointment);
    }

    @Test //TODO
    public void testAddExistingAppointmentToCollection() {
    }

    @Test
    public void testRemoveExistingFromCollection() {
        Appointment appointment = new Appointment();
        boolean result = appointment.addToCollection();
        Assert.assertEquals(result, true);
        Assert.assertEquals(AppointmentsDAO.getAppointments().size(), 1);
        Assert.assertSame(AppointmentsDAO.find(appointment.getAptId()), appointment);
        result = appointment.removeFromCollection();
        Assert.assertEquals(result, true);
        Assert.assertEquals(AppointmentsDAO.getAppointments().size(), 0);
        Assert.assertNull(AppointmentsDAO.find(appointment.getAptId()));
    }

    @Test //TODO
    public void testRemoveNonExistingFromCollection() {
    }
}

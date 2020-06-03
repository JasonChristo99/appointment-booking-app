package com.example.carlaundry.view.Appointments.ManageAppointments;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.view.CleaningStuffHome.CleaningStuffHomePresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManageAppointmentsPresenterTest {
    private ManageAppointmentsPresenter presenter;
    private ManageAppointmentsViewStub view;

    @Before
    public void setUp() {
        view = new ManageAppointmentsViewStub();
        presenter = new ManageAppointmentsPresenter(view);
        Initializer.resetAll();
        Initializer.prepareData();
    }

    /**
     * Tests the case of canceling a pending appointment.
     */
    @Test
    public void cancelAppointment() {
        Appointment appointment = AppointmentsDAO.getPendingAppointments().iterator().next();
        presenter.cancelAppointment(appointment.getAptId());
        Assert.assertEquals("Το ραντεβού διαγράφτηκε επιτυχώς", view.getLog());
    }

}

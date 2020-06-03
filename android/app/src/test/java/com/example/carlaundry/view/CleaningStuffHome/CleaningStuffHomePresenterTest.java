package com.example.carlaundry.view.CleaningStuffHome;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.view.Login.LoginPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CleaningStuffHomePresenterTest {
    private CleaningStuffHomePresenter presenter;
    private CleaningStuffHomeViewStub view;

    @Before
    public void setUp() {
        view = new CleaningStuffHomeViewStub();
        presenter = new CleaningStuffHomePresenter(view);
        Initializer.resetAll();
        Initializer.prepareData();
    }

    /**
     * Tests the case of completing a pending appointment.
     */
    @Test
    public void completeAppointment() {
        Appointment appointment = AppointmentsDAO.getPendingAppointments().iterator().next();
        presenter.completeAppointment(appointment.getAptId(), "a comment");
        Assert.assertEquals("Ο καθαρισμός πραγματοποιήθηκε επιτυχώς", view.getLog());
    }

}

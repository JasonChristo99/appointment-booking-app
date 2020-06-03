package com.example.carlaundry.view.Appointments.ManageAppointments;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.domain.Appointment;

import java.util.ArrayList;
import java.util.List;

public class ManageAppointmentsPresenter {
    private ManageAppointmentsView manageAppointmentsView;

    public ManageAppointmentsPresenter(ManageAppointmentsView manageAppointmentsView) {
        this.manageAppointmentsView = manageAppointmentsView;
    }

    public List<Appointment> getPendingAppointments() {
        return new ArrayList<>(AppointmentsDAO.getPendingAppointments());
    }

    public void cancelAppointment(int aptId) {
        Appointment apt = AppointmentsDAO.find(aptId);
        if (apt != null) {
            apt.cancel();
            manageAppointmentsView.showMessage("Το ραντεβού διαγράφτηκε επιτυχώς");
        } else {
            manageAppointmentsView.showMessage("Το ραντεβού δεν μπόρεσε να διαγραφεί!");
        }
    }
}

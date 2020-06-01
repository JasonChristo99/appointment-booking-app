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
        return new ArrayList<Appointment>(AppointmentsDAO.getPendingAppointments());
    }

    public void onAppointmentCanceled(int aptId) {
        Appointment apt = AppointmentsDAO.find(aptId);
        if (apt != null) {
            apt.cancel();
            manageAppointmentsView.showCancelSuccess();
        } else {
            manageAppointmentsView.showCancelFailed();
        }


    }
}

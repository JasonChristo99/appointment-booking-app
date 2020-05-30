package com.example.carlaundry.view.Appointments;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.domain.Appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AppointmentsPresenter {
    private AppointmentsView appointmentsView;

    public AppointmentsPresenter(AppointmentsView appointmentsView) {
        this.appointmentsView = appointmentsView;
    }

    public List<Appointment> getPendingAppointments() {
        return new ArrayList<Appointment>(AppointmentsDAO.getPendingAppointments());
    }

    public void onAppointmentCanceled(int aptId) {
        Appointment apt = AppointmentsDAO.find(aptId);
        if (apt != null) {
            apt.cancel();
            appointmentsView.showCancelSuccess();
        } else {
            appointmentsView.showCancelFailed();
        }


    }
}

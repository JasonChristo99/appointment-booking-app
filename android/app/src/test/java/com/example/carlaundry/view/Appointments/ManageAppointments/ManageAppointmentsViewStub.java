package com.example.carlaundry.view.Appointments.ManageAppointments;

public class ManageAppointmentsViewStub implements ManageAppointmentsView {
    private String log;

    @Override
    public void showMessage(String message) {
        log = message;
    }


    public String getLog() {
        return log;
    }
}

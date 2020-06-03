package com.example.carlaundry.view.Appointments.AddEditAppointment;

public class AddEditAppointmentViewStub implements AddEditAppointmentView {
    private String log;

    @Override
    public void showMessage(String message) {
        log = message;
    }

    @Override
    public void resultReady() {
    }

    public String getLog() {
        return log;
    }
}

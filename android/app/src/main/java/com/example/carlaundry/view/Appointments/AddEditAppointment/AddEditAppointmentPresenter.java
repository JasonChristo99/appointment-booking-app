package com.example.carlaundry.view.Appointments.AddEditAppointment;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.Car;

import java.time.LocalDateTime;

public class AddEditAppointmentPresenter {
    AddEditAppointmentView addEditAppointmentView;

    public AddEditAppointmentPresenter(AddEditAppointmentView addEditAppointmentView) {
        this.addEditAppointmentView = addEditAppointmentView;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSubmit() {
        AddEditAppointmentActivity view = ((AddEditAppointmentActivity) addEditAppointmentView);
        if (view.customer == null || view.cleanType == null || view.date == null || view.stuffMember == null || view.time == null) {
            addEditAppointmentView.showMessage("Δεν μπορείτε να αφήσετε κάποιο πεδίο κενό!");
            return;
        }
        if (view.mode == AddEditAppointmentActivity.Mode.ADD) {
            Appointment appointment = new Appointment(
                    LocalDateTime.of(view.date, view.time),
                    view.customer,
                    view.stuffMember,
                    view.cleanType,
                    new Car(view.txtInputCarNo.getEditText().getText().toString(), view.txtInputCarManu.getEditText().getText().toString()));
            if (appointment.schedule()) {
                addEditAppointmentView.showMessage("Το ραντεβού καταχωρήθηκε επιτυχώς!");
                addEditAppointmentView.resultReady();
            } else {
                addEditAppointmentView.showMessage("Ο καθαριστής δεν είναι διαθέσιμος την ημερομηνία που επιλέξατε!");
            }
        } else {
            view.editedAppointment.setCleaningType(view.cleanType);
            view.editedAppointment.setAptDate(LocalDateTime.of(view.date, view.time));
            view.editedAppointment.setCar(new Car(view.txtInputCarNo.getEditText().getText().toString(), view.txtInputCarManu.getEditText().getText().toString()));

            if (view.editedAppointment.reschedule()) {
                addEditAppointmentView.showMessage("Οι αλλαγές αποθηκεύτηκαν επιτυχώς!");
                addEditAppointmentView.resultReady();
            } else {
                addEditAppointmentView.showMessage("Ο καθαριστής δεν είναι διαθέσιμος την ημερομηνία που επιλέξατε!");
            }
        }
    }
}

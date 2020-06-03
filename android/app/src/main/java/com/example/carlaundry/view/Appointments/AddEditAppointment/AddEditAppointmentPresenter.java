package com.example.carlaundry.view.Appointments.AddEditAppointment;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.Car;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.CleaningType;
import com.example.carlaundry.domain.Customer;
import com.google.android.material.textfield.TextInputLayout;

import java.awt.font.TextAttribute;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddEditAppointmentPresenter {
    AddEditAppointmentView addEditAppointmentView;

    public AddEditAppointmentPresenter(AddEditAppointmentView addEditAppointmentView) {
        this.addEditAppointmentView = addEditAppointmentView;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onSubmit(AddEditAppointmentActivity.Mode mode, Customer customer, CleaningType cleanType,
                         LocalDate date, CleaningStuffMember stuffMember, LocalTime time, Appointment editedAppointment,
                         String txtInputCarNo, String txtInputCarManu) {
        if (customer == null || cleanType == null || date == null || stuffMember == null || time == null) {
            addEditAppointmentView.showMessage("Δεν μπορείτε να αφήσετε κάποιο πεδίο κενό!");
            return;
        }
        if (mode == AddEditAppointmentActivity.Mode.ADD) {
            Appointment appointment = new Appointment(
                    LocalDateTime.of(date, time),
                    customer,
                    stuffMember,
                    cleanType,
                    new Car(txtInputCarNo, txtInputCarManu));
            if (appointment.schedule()) {
                addEditAppointmentView.showMessage("Το ραντεβού καταχωρήθηκε επιτυχώς!");
                addEditAppointmentView.resultReady();
            } else {
                addEditAppointmentView.showMessage("Ο καθαριστής δεν είναι διαθέσιμος την ημερομηνία που επιλέξατε!");
            }
        } else {
            editedAppointment.setCleaningType(cleanType);
            editedAppointment.setAptDate(LocalDateTime.of(date, time));
            editedAppointment.setCar(new Car(txtInputCarNo, txtInputCarManu));

            if (editedAppointment.reschedule()) {
                addEditAppointmentView.showMessage("Οι αλλαγές αποθηκεύτηκαν επιτυχώς!");
                addEditAppointmentView.resultReady();
            } else {
                addEditAppointmentView.showMessage("Ο καθαριστής δεν είναι διαθέσιμος την ημερομηνία που επιλέξατε!");
            }
        }
    }
}

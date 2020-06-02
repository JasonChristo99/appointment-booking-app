package com.example.carlaundry.view.CleaningStuffHome;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.domain.Appointment;

import java.util.ArrayList;
import java.util.List;

public class CleaningStuffHomePresenter {
    private CleaningStuffHomeView cleaningStuffHomeView;

    public CleaningStuffHomePresenter(CleaningStuffHomeView cleaningStuffHomeView) {
        this.cleaningStuffHomeView = cleaningStuffHomeView;
    }

    public List<Appointment> getStuffPendingAppointments() {
        CleaningStuffHomeActivity view = (CleaningStuffHomeActivity) cleaningStuffHomeView;
        List<Appointment> aptsList = new ArrayList<>(view.loggedInStuffMember.getAssignedPendingAppointments());
        return aptsList;
    }

    public void completeAppointment(int aptId) {
        Appointment apt = AppointmentsDAO.find(aptId);
        if (apt != null) {
            apt.complete();
            cleaningStuffHomeView.showMessage("Ο καθαρισμός πραγματοποιήθηκε επιτυχώς");
        } else {
            cleaningStuffHomeView.showMessage("Ο καθαρισμός δεν μπόρεσε να ολοκληρωθεί");
        }
    }
}

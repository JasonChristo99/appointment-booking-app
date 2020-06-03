package com.example.carlaundry.view.CleaningStuffHome;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.CleaningStuffMember;

import java.util.ArrayList;
import java.util.List;

public class CleaningStuffHomePresenter {
    private CleaningStuffHomeView cleaningStuffHomeView;

    public CleaningStuffHomePresenter(CleaningStuffHomeView cleaningStuffHomeView) {
        this.cleaningStuffHomeView = cleaningStuffHomeView;
    }

    public List<Appointment> getStuffPendingAppointments(CleaningStuffMember loggedInStuffMember) {
        List<Appointment> aptsList = new ArrayList<>(loggedInStuffMember.getAssignedPendingAppointments());
        return aptsList;
    }

    public void completeAppointment(int aptId, String comments) {
        Appointment apt = AppointmentsDAO.find(aptId);
        if (apt != null) {
            apt.complete();
            if (comments != null) {
                apt.setComments(comments);
            }
            cleaningStuffHomeView.showMessage("Ο καθαρισμός πραγματοποιήθηκε επιτυχώς");
        } else {
            cleaningStuffHomeView.showMessage("Ο καθαρισμός δεν μπόρεσε να ολοκληρωθεί");
        }
    }
}

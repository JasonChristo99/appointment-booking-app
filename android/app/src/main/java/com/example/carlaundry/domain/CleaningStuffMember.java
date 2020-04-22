package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CleaningStuffMember extends Person {
    private LocalDate dateHired;
    private WorkHours workHours;

    public CleaningStuffMember(String firstName, String lastName, TelephoneNumber telNumber, EmailAddress emailAddress, int id, LocalDate dateHired) {
        super(firstName, lastName, telNumber, emailAddress, id);
        this.dateHired = dateHired;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(LocalDate dateHired) {
        this.dateHired = dateHired;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHours workHours) {
        this.workHours = workHours;
    }

    public Set<Appointment> getAppointmentsCompleted() {
        Set<Appointment> aptSet = new HashSet<>();
        for (Appointment apt : AppointmentsDAO.getAppointments()) {
            if (this.equals(apt.getStuffMember()) && apt.getAppointmentState().equals(AppointmentState.COMPLETE)) {
                aptSet.add(apt);
            }
        }
        return aptSet;
    }
}

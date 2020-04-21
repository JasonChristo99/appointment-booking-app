package com.example.carlaundry.domain;

import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

import java.time.LocalDateTime;
import java.util.Set;

public class CleaningStuffMember extends Person {
    private LocalDateTime dateHired;

    public CleaningStuffMember(String firstName, String lastName, TelephoneNumber telNumber, EmailAddress emailAddress, int id, LocalDateTime dateHired) {
        super(firstName, lastName, telNumber, emailAddress, id);
        this.dateHired = dateHired;
    }

    public LocalDateTime getDateHired() {
        return dateHired;
    }

    public void setDateHired(LocalDateTime dateHired) {
        this.dateHired = dateHired;
    }

    //TODO
    Set<Appointment> getAppointmentsCompleted(){
        return null;
    }
}

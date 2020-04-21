package com.example.carlaundry.domain;

import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Customer extends Person {
    private LocalDateTime registrationDate;

    public Customer(String firstName, String lastName, TelephoneNumber telNumber, EmailAddress emailAddress, int id, LocalDateTime registrationDate) {
        super(firstName, lastName, telNumber, emailAddress, id);
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    //TODO
    Set<Appointment> getAppointmentsBooked(){
        return null;
    }
}

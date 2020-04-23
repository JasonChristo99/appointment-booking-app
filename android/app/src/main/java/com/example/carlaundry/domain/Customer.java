package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Customer extends Person {
    private LocalDate registrationDate;

    public Customer(String firstName, String lastName, TelephoneNumber telNumber, EmailAddress emailAddress, int id, LocalDate registrationDate) {
        super(firstName, lastName, telNumber, emailAddress, id);
        this.registrationDate = registrationDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Appointment> getAppointmentsBooked() {
        Set<Appointment> aptSet = new HashSet<>();
        for (Appointment apt : AppointmentsDAO.getAppointments()) {
            if (this.equals(apt.getCustomer())) {
                aptSet.add(apt);
            }
        }
        return aptSet;
    }

}

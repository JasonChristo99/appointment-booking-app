package com.example.carlaundry.domain;

import androidx.annotation.NonNull;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Customer extends Person implements Serializable {
    private LocalDate registrationDate;

    public Customer(String firstName, String lastName, TelephoneNumber telNumber, EmailAddress emailAddress, LocalDate registrationDate) {
        super(firstName, lastName, telNumber, emailAddress);
        this.registrationDate = registrationDate;

    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Gathers the pending appointments for this customer and returns it as a set.
     * @return the appointment set
     */
    public Set<Appointment> getAppointmentsBooked() {
        Set<Appointment> aptSet = new HashSet<>();
        for (Appointment apt : AppointmentsDAO.getAppointments()) {
            if (this.equals(apt.getCustomer())) {
                aptSet.add(apt);
            }
        }
        return aptSet;
    }

    public boolean add() {
        return CustomersDAO.add(this);
    }

    public boolean delete() {
        return CustomersDAO.remove(this);
    }

    @NonNull
    @Override
    public String toString() {
        return "Customer " + getEmailAddress().toString();
    }
}

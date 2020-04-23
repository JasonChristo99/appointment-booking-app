package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.CleaningTypesDAO;
import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.Money;
import com.example.carlaundry.util.TelephoneNumber;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;

public class Appointment {
    private int aptId;
    private LocalDateTime aptDate;
    private LocalDateTime aptCompletionDate;
    private Customer customer;
    private CleaningStuffMember stuffMember;
    private CleaningType cleaningType;
    private AppointmentState appointmentState;
    private Car car;
    private String comments;

    public Appointment(int aptId, LocalDateTime aptDate, LocalDateTime aptCompletionDate, Customer customer, CleaningStuffMember stuffMember, CleaningType cleaningType, AppointmentState appointmentState, Car car, String comments) {
        this.aptId = aptId;
        this.aptDate = aptDate;
        this.aptCompletionDate = aptCompletionDate;
        this.customer = customer;
        this.stuffMember = stuffMember;
        this.cleaningType = cleaningType;
        this.appointmentState = appointmentState;
        this.car = car;
        this.comments = comments;
    }

    public int getAptId() {
        return aptId;
    }

    public LocalDateTime getAptDate() {
        return aptDate;
    }

    public LocalDateTime getAptCompletionDate() {
        return aptCompletionDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CleaningStuffMember getStuffMember() {
        return stuffMember;
    }

    public CleaningType getCleaningType() {
        return cleaningType;
    }

    public AppointmentState getAppointmentState() {
        return appointmentState;
    }

    public Car getCar() {
        return car;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public int hashCode() {
        return aptId;
    }

    public boolean schedule() {
        for (Appointment appointment : AppointmentsDAO.getAppointments()) {
            if (appointment.getStuffMember().isAvailableOn(appointment.getAptDate())) {
                return AppointmentsDAO.add(this);
            }
        }
        return false;
    }
}

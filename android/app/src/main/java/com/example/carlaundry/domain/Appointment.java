package com.example.carlaundry.domain;

import com.example.carlaundry.dao.AppointmentsDAO;

import java.time.LocalDateTime;

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

    public Appointment() {
    }

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

    public void setAptId(int aptId) {
        this.aptId = aptId;
    }

    public LocalDateTime getAptDate() {
        return aptDate;
    }

    public void setAptDate(LocalDateTime aptDate) {
        this.aptDate = aptDate;
    }

    public LocalDateTime getAptCompletionDate() {
        return aptCompletionDate;
    }

    public void setAptCompletionDate(LocalDateTime aptCompletionDate) {
        this.aptCompletionDate = aptCompletionDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CleaningStuffMember getStuffMember() {
        return stuffMember;
    }

    public void setStuffMember(CleaningStuffMember stuffMember) {
        this.stuffMember = stuffMember;
    }

    public CleaningType getCleaningType() {
        return cleaningType;
    }

    public void setCleaningType(CleaningType cleaningType) {
        this.cleaningType = cleaningType;
    }

    public AppointmentState getAppointmentState() {
        return appointmentState;
    }

    public void setAppointmentState(AppointmentState appointmentState) {
        this.appointmentState = appointmentState;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        return aptId;
    }

    public boolean addToCollection() {
        return AppointmentsDAO.getAppointments().add(this);
    }

    public boolean removeFromCollection(int id) {
        Appointment appointment = AppointmentsDAO.find(id);
        if (appointment == null) {
            return false;
        }
        return AppointmentsDAO.getAppointments().remove(appointment);
    }
}

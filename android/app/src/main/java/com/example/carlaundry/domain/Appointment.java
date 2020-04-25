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
    private AppointmentState aptState;
    private Car car;
    private String comments;

    public Appointment(int aptId, LocalDateTime aptDate, Customer customer, CleaningStuffMember stuffMember, CleaningType cleaningType, Car car) {
        this.aptId = aptId;
        this.aptDate = aptDate;
        this.customer = customer;
        this.stuffMember = stuffMember;
        this.cleaningType = cleaningType;
        this.car = car;
        this.aptState = AppointmentState.PENDING;
    }

    public Appointment(int aptId, LocalDateTime aptDate, LocalDateTime aptCompletionDate, Customer customer, CleaningStuffMember stuffMember, CleaningType cleaningType, AppointmentState aptState, Car car, String comments) {
        this.aptId = aptId;
        this.aptDate = aptDate;
        this.aptCompletionDate = aptCompletionDate;
        this.customer = customer;
        this.stuffMember = stuffMember;
        this.cleaningType = cleaningType;
        this.aptState = aptState;
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

    public AppointmentState getAptState() {
        return aptState;
    }

    public Car getCar() {
        return car;
    }

    public String getComments() {
        return comments;
    }

    public void setAptCompletionDate(LocalDateTime aptCompletionDate) {
        this.aptCompletionDate = aptCompletionDate;
    }

    public void setAptState(AppointmentState aptState) {
        this.aptState = aptState;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        return aptId;
    }

    public boolean schedule() {
        if (this.getStuffMember().isAvailableOn(this.getAptDate())) {
            return AppointmentsDAO.add(this);
        }
        return false;
    }

    public boolean cancel() {
        if (!this.getAptState().equals(AppointmentState.PENDING)) {
            return false;
        }
        if (AppointmentsDAO.remove(this)) {
            this.setAptState(AppointmentState.CANCELED);
            return true;
        }
        return false;
    }

    public boolean complete() {
        if (aptState.equals(AppointmentState.PENDING)) {
            aptState = AppointmentState.COMPLETE;
            return true;
        }
        return false;
    }
}

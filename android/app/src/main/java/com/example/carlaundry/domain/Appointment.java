package com.example.carlaundry.domain;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

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

    private static int next_id = 1;

    public Appointment(int aptId, LocalDateTime aptDate, Customer customer, CleaningStuffMember stuffMember, CleaningType cleaningType, Car car) {
        this.aptId = aptId;
        this.aptDate = aptDate;
        this.customer = customer;
        this.stuffMember = stuffMember;
        this.cleaningType = cleaningType;
        this.car = car;
        this.aptState = AppointmentState.PENDING;
    }

    public Appointment(LocalDateTime aptDate, Customer customer, CleaningStuffMember stuffMember, CleaningType cleaningType, Car car) {
        this.aptId = nextId();
        this.aptDate = aptDate;
        this.customer = customer;
        this.stuffMember = stuffMember;
        this.cleaningType = cleaningType;
        this.aptState = AppointmentState.INVALID;
        this.car = car;
    }

    /**
     * Fetches a new id for the appointment being currently created.
     * @return the next id
     */
    private static int nextId() {
        next_id++;
        return next_id - 1;
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

    public void setAptDate(LocalDateTime aptDate) {
        this.aptDate = aptDate;
    }

    public void setCleaningType(CleaningType cleaningType) {
        this.cleaningType = cleaningType;
    }

    public void setCar(Car car) {
        this.car = car;
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

    @NonNull
    @Override
    public String toString() {
        return "Appointment on " + getAptDate().toString() + " served by " + getStuffMember().toString();
    }


    /**
     * Schedules a created appointment, checking whether
     * the selected cleaner is available on the selected date.
     * Also adds the new appointment to the DAO.
     * @return true if the appointment was scheduled successfully
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean schedule() {
        if (this.getStuffMember().isAvailableOn(this.getAptDate())) {
            if (AppointmentsDAO.add(this)) {
                aptState = AppointmentState.PENDING;
                return true;
            }
        }
        aptState = AppointmentState.INVALID;
        return false;
    }

    /**
     * Reschedules a created appointment, after the admin has chosen
     * to edit its details, checking whether the selected cleaner
     * is available on the selected date. This method does not add
     * the appointment to the DAO.
     * @return true if the appointment was rescheduled successfully
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean reschedule() {
        if (this.getStuffMember().isAvailableOn(this.getAptDate())) {
            aptState = AppointmentState.PENDING;
            return true;
        }
        aptState = AppointmentState.INVALID;
        return false;
    }

    /**
     * Checks the appointment's state and if it is pending,
     * the method sets the appointment's state to canceled.
     * @return true if the appointment was canceled successfully
     */
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

    /**
     * Checks the appointment's state and if it is pending,
     * the method sets the appointment's state to complete.
     * @return true if the appointment was completed successfully
     */
    public boolean complete() {
        if (aptState.equals(AppointmentState.PENDING)) {
            aptState = AppointmentState.COMPLETE;
            return true;
        }
        return false;
    }
}

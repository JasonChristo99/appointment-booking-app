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

    public Appointment() {
        this.aptId = 1;
        this.aptDate = LocalDateTime.of(2020, 1, 1, 0, 0);
        this.aptCompletionDate = LocalDateTime.of(2020, 1, 2, 0, 0);
        this.customer = new Customer("Dum", "Cust", new TelephoneNumber("6999999999"), new EmailAddress("a@b.com"), 1, LocalDate.of(2020, 1, 1));
        this.stuffMember = new CleaningStuffMember("Dum", "Stuf", new TelephoneNumber("6999999999"), new EmailAddress("a@b.com"), 1, LocalDate.of(2020, 1, 1));
        this.cleaningType = new CleaningType("bio", new Money(new BigDecimal(10), Currency.getInstance("EUR")), Duration.ofHours(1), 1);
        this.appointmentState = AppointmentState.PENDING;
        this.car = new Car("ABC", "Manuf", "model");
        this.comments = "none";
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

    public boolean removeFromCollection() {
        return AppointmentsDAO.getAppointments().remove(this);
    }
}

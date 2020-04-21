package com.example.carlaundry.domain;

import androidx.annotation.Nullable;

import com.example.carlaundry.util.Address;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

public class Person {
    private String firstName;
    private String lastName;
    private TelephoneNumber telNumber;
    private EmailAddress emailAddress;
    private Address address;
    private int id;

    public Person() {
    }

    public Person(String firstName, String lastName, TelephoneNumber telNumber, EmailAddress emailAddress, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.emailAddress = emailAddress;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TelephoneNumber getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(TelephoneNumber telNumber) {
        this.telNumber = telNumber;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override //TODO
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}

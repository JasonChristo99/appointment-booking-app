package com.example.carlaundry.domain;

import androidx.annotation.Nullable;

import com.example.carlaundry.util.Address;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

public class Person {
    private String firstName;
    private String lastName;
    private TelephoneNumber telNo;
    private EmailAddress emailAddress;
    private Address address;

    public Person() {
    }

    public Person(String firstName, String lastName, TelephoneNumber telNo, EmailAddress emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNo = telNo;
        this.emailAddress = emailAddress;
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

    public TelephoneNumber getTelNo() {
        return telNo;
    }

    public String getTelNoAsString() {
        return telNo.stringTelephone();
    }



    public void setTelNo(TelephoneNumber telNo) {
        this.telNo = telNo;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Person)) {
            return false;
        }
        Person otherPerson = (Person) other;
        return emailAddress == null ? otherPerson.getEmailAddress() == null : emailAddress == (otherPerson.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return emailAddress.hashCode();
    }
}

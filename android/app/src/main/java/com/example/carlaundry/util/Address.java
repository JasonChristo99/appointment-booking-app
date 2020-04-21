package com.example.carlaundry.util;

public class Address {
    private String street;
    private int streetNo;
    private int zipCode;
    private String city;

    //TODO constructor
    public Address(String street, int streetNo, int zipCode, String city) {
        this.street = street;
        this.streetNo = streetNo;
        this.zipCode = zipCode;
        this.city = city;
    }

    //TODO
    private boolean isValid() {
        return false;
    }

}

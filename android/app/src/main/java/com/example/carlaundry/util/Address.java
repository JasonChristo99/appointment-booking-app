package com.example.carlaundry.util;

public class Address {
    private String street;
    private int streetNo;
    private int zipCode;
    private String city;

    public Address(String street, int streetNo, int zipCode, String city) {
        this.street = street;
        this.streetNo = streetNo;
        this.zipCode = zipCode;
        this.city = city;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid() {
        return street != null && String.valueOf(streetNo).length() > 0 && String.valueOf(zipCode).length() == 5 && city != null;
    }

}

package com.example.carlaundry.domain;

public class Car {
    private String registrationNumber;
    private String manufacturer;

    public Car(String registrationNumber, String manufacturer) {
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public int hashCode() {
        return registrationNumber.hashCode();
    }
}

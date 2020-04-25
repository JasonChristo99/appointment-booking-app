package com.example.carlaundry.domain;

public class Car {
    private String registrationNumber;
    private String manufacturer;
    private String model;

    public Car(String registrationNumber, String manufacturer, String model) {
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    @Override
    public int hashCode() {
        return registrationNumber.hashCode();
    }
}

package com.example.carlaundry.domain;

import java.util.Calendar;

public class Appointment {
    private Calendar date = Calendar.getInstance();

    public Appointment() {
    }

    public Calendar getDate() {
        return date;
    }
}

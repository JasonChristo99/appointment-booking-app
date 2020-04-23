package com.example.carlaundry.util;

import java.util.Currency;

public class Money {
    private double value;
    private Currency currency;

    public Money(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

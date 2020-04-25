package com.example.carlaundry.util;

import androidx.annotation.Nullable;

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

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Money)) {
            return false;
        }
        Money otherMoney = (Money) other;
        return value == 0 ? otherMoney.getValue() == 0 : value == (otherMoney.getValue()) && currency.equals(otherMoney.getCurrency());
    }
}

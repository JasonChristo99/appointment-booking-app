package com.example.carlaundry.util;

import java.math.BigDecimal;
import java.util.Currency;

public class Money {
    private BigDecimal value;
    private Currency currency;

    public Money(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

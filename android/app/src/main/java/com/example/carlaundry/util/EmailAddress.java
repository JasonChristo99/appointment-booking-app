package com.example.carlaundry.util;

import java.util.regex.Pattern;

public class EmailAddress {
    private String address;

    public EmailAddress(String address) {
        this.address = address;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid() {
        if (this.address == null) {
            return false;
        }
        return Pattern.matches("^[A-Z0-9._%+-][A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{3}$", address);
    }
}

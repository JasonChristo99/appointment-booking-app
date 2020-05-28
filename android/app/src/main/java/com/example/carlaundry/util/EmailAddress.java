package com.example.carlaundry.util;

import androidx.annotation.Nullable;

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
        return Pattern.matches("^(.+)@(.+)$", address.toUpperCase());
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof EmailAddress)) {
            return false;
        }
        return address.equals(((EmailAddress) obj).getAddress());
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }
}

package com.example.carlaundry.util;

import com.example.carlaundry.dao.Initializer;

import java.util.regex.Pattern;

public class TelephoneNumber {
    private String telNo;

    public TelephoneNumber(String telNo) {
        this.telNo = telNo;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public String getTelNo() {
        return telNo;
    }

    private boolean isValid() {
        return Pattern.matches("^69\\d{8}$", telNo) || Pattern.matches("^210\\d{7}$", telNo);
    }

    public int intValue() {
        return Integer.parseInt(telNo);
    }

}

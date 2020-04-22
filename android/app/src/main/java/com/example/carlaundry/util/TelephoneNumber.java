package com.example.carlaundry.util;

import java.util.regex.Pattern;

public class TelephoneNumber {
    private int telNo;

    public TelephoneNumber(int telNo) {
        this.telNo = telNo;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public int getTelNo() {
        return telNo;
    }

    private boolean isValid() {
        return Pattern.matches("^69\\d{8}$", String.valueOf(telNo)) || Pattern.matches("^210\\d{7}$", String.valueOf(telNo));
    }

}

package com.example.carlaundry.util;

import java.util.regex.Pattern;

public class AFM {
    private String afm;

    public AFM(String afm) {
        this.afm = afm;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid() {
        return Pattern.matches("^\\d{9}$", afm);
    }

    public int getIntValue() {
        return Integer.parseInt(afm);
    }
}

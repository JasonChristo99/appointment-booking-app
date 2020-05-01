package com.example.carlaundry.util;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DailyTimePeriod {
    private LocalTime startHour;
    private LocalTime endHour;

    public DailyTimePeriod(LocalTime startHour, LocalTime endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    @Override
    public int hashCode() {
        return getStartHour().toString().concat(getEndHour().toString()).hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return getStartHour() + " to " + getEndHour();
    }
}

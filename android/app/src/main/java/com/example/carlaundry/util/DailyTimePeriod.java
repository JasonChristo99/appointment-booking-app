package com.example.carlaundry.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DailyTimePeriod {
    LocalTime startHour;
    LocalTime endHour;

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
}

package com.example.carlaundry.domain;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WorkHours {
    private Map<DayOfWeek, Duration> workHoursMap;

    public WorkHours(Map<DayOfWeek, Duration> workHoursMap) {
        this.workHoursMap = workHoursMap;
    }

    public Map<DayOfWeek, Duration> getWorkHoursMap() {
        return workHoursMap;
    }

    public void setWorkHoursMap(Map<DayOfWeek, Duration> workHoursMap) {
        this.workHoursMap = workHoursMap;
    }
}

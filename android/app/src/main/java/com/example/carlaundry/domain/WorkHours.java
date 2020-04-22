package com.example.carlaundry.domain;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkHours {

    private Map<DayOfWeek, List<LocalTime>> workHoursMap;

    public WorkHours(Map<DayOfWeek, List<LocalTime>> workHoursMap) {
        this.workHoursMap = workHoursMap;
    }

    public Map<DayOfWeek, List<LocalTime>> getWorkHoursMap() {
        return workHoursMap;
    }

    public void setWorkHoursMap(Map<DayOfWeek, List<LocalTime>> workHoursMap) {
        this.workHoursMap = workHoursMap;
    }
}

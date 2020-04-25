package com.example.carlaundry.domain;

import com.example.carlaundry.util.DailyTimePeriod;

import java.time.DayOfWeek;
import java.util.Map;

public class WorkHours {

    private Map<DayOfWeek, DailyTimePeriod> workHoursMap;

    public WorkHours(Map<DayOfWeek, DailyTimePeriod> workHoursMap) {
        this.workHoursMap = workHoursMap;
    }

    public Map<DayOfWeek, DailyTimePeriod> getWorkHoursMap() {
        return workHoursMap;
    }

    public void setWorkHoursMap(Map<DayOfWeek, DailyTimePeriod> workHoursMap) {
        this.workHoursMap = workHoursMap;
    }

    @Override
    public int hashCode() {
        return workHoursMap.hashCode();
    }
}

package com.example.carlaundry.view.CleaningStuff;

import com.example.carlaundry.util.DailyTimePeriod;

import java.time.DayOfWeek;
import java.util.Map;

public interface CleaningStuffView {
    void createWorkHours(Map<DayOfWeek, DailyTimePeriod> workHours);
    void navigateToCleaningStuffActivity();

    void nagivateToCleaningStuffViewEdit();

    void nagivateToCleaningStuffAdd();

    void navigateToCleaningStuffEditWorkHours(String stringmail);

    void wrongData();


}

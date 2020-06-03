package com.example.carlaundry.view.CleaningStuff;

import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.view.Statistics.StatisticsView;

import java.time.DayOfWeek;
import java.util.Map;

public class CleaningStuffViewStub implements CleaningStuffView {
    private String log;


    public String getLog() {
        return log;
    }

    @Override
    public void createWorkHours(Map<DayOfWeek, DailyTimePeriod> workHours) {

    }

    @Override
    public void navigateToCleaningStuffActivity() {
        log = "ok";
    }

    @Override
    public void nagivateToCleaningStuffViewEdit() {

    }

    @Override
    public void nagivateToCleaningStuffAdd() {
    }

    @Override
    public void navigateToCleaningStuffEditWorkHours(String stringmail) {

    }

    @Override
    public void wrongData() {
        log = "error";
    }
}

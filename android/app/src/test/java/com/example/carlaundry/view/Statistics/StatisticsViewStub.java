package com.example.carlaundry.view.Statistics;

import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.view.Login.LoginView;

public class StatisticsViewStub implements StatisticsView {
    private String log;

    @Override
    public void showMessage(String message) {
        log = message;
    }


    public String getLog() {
        return log;
    }

}

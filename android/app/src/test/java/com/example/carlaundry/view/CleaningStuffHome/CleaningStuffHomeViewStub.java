package com.example.carlaundry.view.CleaningStuffHome;

import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.view.CleaningStuff.CleaningStuffView;
import com.example.carlaundry.view.Login.LoginView;

public class CleaningStuffHomeViewStub implements CleaningStuffHomeView {
    private String log;

    @Override
    public void showMessage(String message) {
        log = message;
    }


    public String getLog() {
        return log;
    }
}

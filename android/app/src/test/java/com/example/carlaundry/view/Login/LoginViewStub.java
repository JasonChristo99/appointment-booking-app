package com.example.carlaundry.view.Login;

import com.example.carlaundry.util.EmailAddress;

public class LoginViewStub implements LoginView {
    private String log;

    @Override
    public void navigateToAdminHome() {

    }

    @Override
    public void navigateToStuffHome(EmailAddress emailAddress) {

    }

    @Override
    public void showMessage(String message) {
        log = message;
    }


    public String getLog() {
        return log;
    }
}

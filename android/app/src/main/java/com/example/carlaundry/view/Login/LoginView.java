package com.example.carlaundry.view.Login;

import com.example.carlaundry.util.EmailAddress;

public interface LoginView {
    void navigateToAdminHome();

    void navigateToStuffHome(EmailAddress emailAddress);

    void showMessage(String message);
}

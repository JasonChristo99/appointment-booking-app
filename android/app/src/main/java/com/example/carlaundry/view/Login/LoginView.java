package com.example.carlaundry.view.Login;

import com.example.carlaundry.util.EmailAddress;

public interface LoginView {
    void showSuccessMessage();

    void showFailureMessage();

    void showIllegalEmailError();

    void navigateToAdminHome();

    void navigateToStuffHome(EmailAddress emailAddress);
}

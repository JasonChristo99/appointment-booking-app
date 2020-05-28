package com.example.carlaundry.view.Login;

public interface LoginView {
    void showSuccess();

    void showFailure();

    void showIllegalEmailError();

    void navigateToAdminHome();

    void navigateToStuffHome();
}

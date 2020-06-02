package com.example.carlaundry.view.Login;

import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.dao.UserAccountsDAO;
import com.example.carlaundry.domain.UserAccount;
import com.example.carlaundry.util.EmailAddress;

public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        Initializer.prepareData();
        this.loginView = loginView;
    }

    public void verifyUser(String stringEmail) {
        try {
            EmailAddress emailAddress = new EmailAddress(stringEmail);
            boolean success = UserAccountsDAO.verify(emailAddress);
            if (success) {
                loginView.showSuccessMessage();
                UserAccount userAccount = UserAccountsDAO.find(emailAddress);
                if (userAccount.getAccountType().equals(UserAccount.AccountType.ADMIN)) {
                    loginView.navigateToAdminHome();
                } else {
                    loginView.navigateToStuffHome(emailAddress);
                }
            } else {
                loginView.showFailureMessage();
            }
        } catch (IllegalArgumentException e) {
            loginView.showIllegalEmailError();
        }

    }


}

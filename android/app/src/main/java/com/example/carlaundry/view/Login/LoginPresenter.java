package com.example.carlaundry.view.Login;

import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.dao.UserAccountsDAO;
import com.example.carlaundry.domain.UserAccount;
import com.example.carlaundry.util.EmailAddress;

public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        prepareData();
        this.loginView = loginView;
    }

    private void prepareData() {
        Initializer.resetAll();
        Initializer.prepareData();
    }

    public void verifyUser(String stringEmail) {
        try {
            EmailAddress emailAddress = new EmailAddress(stringEmail);
            boolean success = UserAccountsDAO.verify(emailAddress);
            if (success) {
                UserAccount userAccount = UserAccountsDAO.find(emailAddress);
                if (userAccount.getAccountType().equals(UserAccount.AccountType.ADMIN)) {
                    loginView.showMessage("Επιτυχημένη είσοδος ως διαχειριστής");
                    loginView.navigateToAdminHome();
                } else if (userAccount.getAccountType().equals(UserAccount.AccountType.STUFF)) {
                    loginView.showMessage("Επιτυχημένη είσοδος ως καθαριστής");
                    loginView.navigateToStuffHome(emailAddress);
                }
            } else {
                loginView.showMessage("H είσοδος απέτυχε");
            }
        } catch (IllegalArgumentException e) {
            loginView.showMessage("Παρακαλώ εισάγετε μία έγκυρη διεύθυνση email");
        }

    }


}

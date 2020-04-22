package com.example.carlaundry.util;

import androidx.annotation.Nullable;

import com.example.carlaundry.dao.AccountsDAO;

import java.util.regex.Pattern;

public class UserAccount {
    private enum AccountType {ADMIN, STUFF}

    private AccountType accountType;
    private String userId;
    private String password;

    public UserAccount(String userId, String password) {
        this.userId = userId;
        this.password = password;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserAccount)) {
            return false;
        }
        UserAccount otherPerson = (UserAccount) other;
        return userId == null ? otherPerson.getUserId() == null : userId.equals(otherPerson.getUserId());
    }

    private boolean isValid() {
        if (this.userId == null || this.password == null) {
            return false;
        }
        return isUnique();
    }

    private boolean isUnique() {
        if (accountType.equals(AccountType.ADMIN)) {
            return true;
        }
        for (UserAccount acc : AccountsDAO.getUserAccounts()) {
            if (this.equals(acc)) {
                return true;
            }
        }
        return false;
    }
}

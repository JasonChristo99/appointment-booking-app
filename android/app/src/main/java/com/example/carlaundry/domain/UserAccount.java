package com.example.carlaundry.domain;

import androidx.annotation.Nullable;

import com.example.carlaundry.dao.UserAccountsDAO;

public class UserAccount {
    public enum AccountType {ADMIN, STUFF}

    private AccountType accountType;
    private String userId;
    private String password;
    private int stuffId;

    public UserAccount(String userId, String password, AccountType accountType, int stuffId) {
        this.userId = userId;
        this.password = password;
        this.accountType = accountType;
        this.stuffId = stuffId; // stuffId must be negative for administrator accounts
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public int getStuffId() {
        return stuffId;
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
        UserAccount otherAccount = (UserAccount) other;
        return userId.equals(otherAccount.getUserId()) && password.equals(otherAccount.getPassword()) && accountType.equals(otherAccount.getAccountType());
    }

    private boolean isValid() {
        if (accountType.equals(AccountType.STUFF) && stuffId <= 0) {
            return false;
        }
        if (accountType.equals(AccountType.STUFF) && stuffId > 0) {
            return false;
        }
        return this.userId != null && this.password != null && this.accountType != null;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    public boolean register() {
        return UserAccountsDAO.add(this);
    }

    public boolean verify() {
        return UserAccountsDAO.verify(this);
    }

    public boolean delete() {
        return UserAccountsDAO.remove(this);
    }
}

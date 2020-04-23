package com.example.carlaundry.domain;

import androidx.annotation.Nullable;

import com.example.carlaundry.dao.UserAccountsDAO;

public class UserAccount {
    public enum AccountType {ADMIN, STUFF}

    private AccountType accountType;
    private String userId;
    private String password;

    public UserAccount(String userId, String password, AccountType accountType) {
        this.userId = userId;
        this.password = password;
        this.accountType = accountType;
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
        return this.userId != null && this.password != null && this.accountType != null;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    public boolean addToCollection() {
        return UserAccountsDAO.getUserAccounts().add(this);
    }

    public boolean removeFromCollection(String id) {
        return UserAccountsDAO.getUserAccounts().remove(this);
    }
}

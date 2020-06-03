package com.example.carlaundry.domain;

import androidx.annotation.Nullable;

import com.example.carlaundry.dao.UserAccountsDAO;
import com.example.carlaundry.util.EmailAddress;

public class UserAccount {
    public enum AccountType {ADMIN, STUFF}

    private AccountType accountType;
    private EmailAddress emailAddress;

    public UserAccount(EmailAddress emailAddress, AccountType accountType) {
        this.emailAddress = emailAddress;
        this.accountType = accountType;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
        register();
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
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
        return emailAddress.equals(otherAccount.getEmailAddress());
    }

    private boolean isValid() {
        return UserAccountsDAO.find(emailAddress) == null;
    }

    @Override
    public int hashCode() {
        return emailAddress.hashCode();
    }

    /**
     * Adds the user to the respective DAO.
     * @return true if added successfully
     */
    public boolean register() {
        return UserAccountsDAO.add(this);
    }

    /**
     * Checks if the user is already registered.
     * @return true if the user is found in the DAO
     */
    public boolean verify() {
        return UserAccountsDAO.verify(this.emailAddress);
    }

    /**
     * Removes the user to the respective DAO.
     * @return true if removed successfully
     */
    public boolean delete() {
        return UserAccountsDAO.remove(this);
    }
}

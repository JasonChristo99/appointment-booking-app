package com.example.carlaundry.dao;

import com.example.carlaundry.domain.UserAccount;
import com.example.carlaundry.util.EmailAddress;

import java.util.HashSet;
import java.util.Set;

public class UserAccountsDAO {
    private static Set<UserAccount> userAccounts = new HashSet<>();

    public static Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public static UserAccount find(EmailAddress emailAddress) {
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getEmailAddress().equals(emailAddress)) {
                return userAccount;
            }
        }
        return null;
    }

    public static boolean verify(EmailAddress emailAddress) {
        for (UserAccount account : userAccounts) {
            if (account.getEmailAddress().equals(emailAddress)) {
                return true;
            }
        }
        return false;
    }

    public static boolean add(UserAccount userAccount) {
        return userAccounts.add(userAccount);
    }

    public static boolean remove(UserAccount userAccount) {
        return userAccounts.remove(userAccount);
    }

    public static void reset() {
        userAccounts = new HashSet<>();
    }
}

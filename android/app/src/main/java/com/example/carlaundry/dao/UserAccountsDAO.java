package com.example.carlaundry.dao;

import com.example.carlaundry.domain.UserAccount;

import java.util.HashSet;
import java.util.Set;

public class UserAccountsDAO {
    private static Set<UserAccount> userAccounts = new HashSet<>();

    public static Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public static UserAccount find(String userId) {
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getUserId().equals(userId)) {
                return userAccount;
            }
        }
        return null;
    }
}

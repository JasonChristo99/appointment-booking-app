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

    public static UserAccount findStuff(int stuffId) {
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getStuffId() == stuffId && userAccount.getAccountType().equals(UserAccount.AccountType.STUFF)) {
                return userAccount;
            }
        }
        return null;
    }

    public static boolean verify(UserAccount userAccount) {
        for (UserAccount account : userAccounts) {
            if (account.equals(userAccount)) {
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

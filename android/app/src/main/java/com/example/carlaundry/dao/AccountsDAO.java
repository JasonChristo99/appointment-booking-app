package com.example.carlaundry.dao;

import com.example.carlaundry.util.UserAccount;

import java.util.HashSet;
import java.util.Set;

public class AccountsDAO {
    private static Set<UserAccount> userAccounts = new HashSet<>();

    public static Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    //TODO
    public static boolean addUserAccount(UserAccount account) {
        return false;
    }

    //TODO
    public static boolean removeUserAccount(String userId) {
        return false;
    }
}

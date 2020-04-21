package com.example.carlaundry.dao;

import com.example.carlaundry.util.UserAccount;

import java.util.HashSet;
import java.util.Set;

public class AccountsDAO {
    private UserAccount adminAccount;
    private Set<UserAccount> cleaningStuffAccounts = new HashSet<>();

    public UserAccount getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(UserAccount adminAccount) {
        this.adminAccount = adminAccount;
    }

    public Set<UserAccount> getCleaningStuffAccounts() {
        return cleaningStuffAccounts;
    }

    //TODO
    public static boolean addStuffAccount(UserAccount account) {
        return false;
    }

    //TODO
    public static boolean removeStuffAccount(String userId) {
        return false;
    }
}

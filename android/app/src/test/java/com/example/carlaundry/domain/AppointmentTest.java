package com.example.carlaundry.domain;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class AppointmentTest {
    @Test
    public void exampleTest() {
        Set<UserAccount> userAccounts = new HashSet<>();
        UserAccount acc1 = new UserAccount("id1", "pas1", UserAccount.AccountType.STUFF);
        // add an account
        System.out.println(userAccounts.add(acc1));
        // the same
        System.out.println(userAccounts.add(acc1));
        UserAccount acc2 = new UserAccount("id1", "pas1", UserAccount.AccountType.STUFF);
        // add not same, but equal
        System.out.println(userAccounts.add(acc2));
    }
}

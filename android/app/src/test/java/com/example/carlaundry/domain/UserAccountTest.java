package com.example.carlaundry.domain;

import com.example.carlaundry.dao.Initializer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserAccountTest {

    @Before
    public void setup() {
        Initializer.resetAll();
    }

    @Test
    public void signUpNonExistingUser() {
        // sign up an admin account
        UserAccount user1 = new UserAccount("user1", "user1", UserAccount.AccountType.ADMIN, -10);
        boolean result = user1.register();
        Assert.assertTrue(result);
    }

    @Test
    public void signUpExistingUser() {
        UserAccount user1 = new UserAccount("user1", "user1", UserAccount.AccountType.ADMIN, -10);
        user1.register(); //first register
        boolean result = user1.register(); //second register
        Assert.assertFalse(result);
    }

    @Test
    public void verifyExistingUser() {
        //create a user account and register him
        UserAccount user1 = new UserAccount("user1", "user1", UserAccount.AccountType.STUFF, 1);
        user1.register();
        boolean result = user1.verify();
        Assert.assertTrue(result);
    }

    @Test
    public void verifyNonExistingUser() {
        // create an account that registered and then deleted
        UserAccount user1 = new UserAccount("user1", "user1", UserAccount.AccountType.ADMIN, -10);
        user1.register();
        user1.delete();
        boolean result = user1.verify();
        Assert.assertFalse(result);
    }
}

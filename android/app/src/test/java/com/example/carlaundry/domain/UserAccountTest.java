package com.example.carlaundry.domain;

import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.dao.UserAccountsDAO;
import com.example.carlaundry.util.EmailAddress;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserAccountTest {

    @Before
    public void setup() {
        Initializer.resetAll();
    }

    /**
     * Tests the registration operation on a new user.
     */
    @Test
    public void signUpNonExistingUser() {
        // sign up an admin account
        CleaningStuffMember stuffMember = Initializer.getDummyCleaningStuffMember();
        EmailAddress userEmail = stuffMember.getEmailAddress();
        Assert.assertNotNull(UserAccountsDAO.find(userEmail));
    }

    /**
     * Tests the registration operation on an already
     * registered user, which should be prohibited.
     */
    @Test
    public void signUpExistingUser() {
        // sign up an admin account
        CleaningStuffMember stuffMember = Initializer.getDummyCleaningStuffMember();
        boolean result = stuffMember.getUserAccount().register(); // register for second time
        Assert.assertFalse(result);
    }

    /**
     * Tests the verification operation on a registered user.
     */
    @Test
    public void verifyExistingUser() {
        // sign up an admin account
        CleaningStuffMember stuffMember = Initializer.getDummyCleaningStuffMember();
        UserAccount userAccount = stuffMember.getUserAccount();
        boolean result = userAccount.verify();
        Assert.assertTrue(result);
    }

    /**
     * Tests the verification operation on an unregistered user, which should be prohibited.
     */
    @Test
    public void verifyNonExistingUser() {
        // create an account that registered and then deleted
        CleaningStuffMember stuffMember = Initializer.getDummyCleaningStuffMember();
        stuffMember.hire();
        boolean result = stuffMember.fire();
        Assert.assertTrue(result);
        stuffMember.fire();
        result = stuffMember.getUserAccount().verify();
        Assert.assertFalse(result);
    }
}

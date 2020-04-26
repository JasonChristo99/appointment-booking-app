

package com.example.carlaundry.domain;
import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.domain.WorkHours;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.UserAccountsDAO;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;


public class CleaningStuffMemberTest  {

    @Before
    public void setUp() {
        Initializer.resetAll();
    }

    @Test
    public void fire() {
        // create stuff member and add him to the list of cleaners
        CleaningStuffMember stuffMember = Initializer.getDummyCleaningStuffMember();
        CleaningStuffDAO.add(stuffMember);
        // create an account for the cleaner
        UserAccount cleaner = new UserAccount("cleaner1","cleaner1", UserAccount.AccountType.STUFF,1);
        cleaner.register();
        //remove the cleaning stuff member from the list
        boolean result = stuffMember.fire();
        Assert.assertTrue(result);}







}

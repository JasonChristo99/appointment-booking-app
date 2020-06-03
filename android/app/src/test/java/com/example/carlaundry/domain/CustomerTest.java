package com.example.carlaundry.domain;

import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.dao.Initializer;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

    /**
     * Tests the delete operation on a registered customer.
     */
    @Test
    public void delete() {
        //create a customer
        Customer customer = Initializer.getDummyCustomer();
        boolean result = customer.add();
        Assert.assertTrue(result);
        // remove customer
        result = customer.delete();
        //test the removal process
        Assert.assertTrue(result);
    }
}

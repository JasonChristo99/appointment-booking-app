package com.example.carlaundry.domain;

import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.dao.Initializer;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    @Test //TODO
    public void delete() {
        //create a customer
        Customer customer = Initializer.getDummyCustomer();
        CustomersDAO.add(customer);
        // remove customer
        boolean result = customer.delete();
        //test the removal process
        Assert.assertTrue(result);
    }
}

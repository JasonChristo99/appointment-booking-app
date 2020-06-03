package com.example.carlaundry.view.Customers;

import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.CustomersDAO;
import com.example.carlaundry.dao.Initializer;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.Customer;
import com.example.carlaundry.view.CleaningStuff.CleaningStuffPresenter;
import com.example.carlaundry.view.CleaningStuff.CleaningStuffViewStub;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

public class CustomersPresenterTest {
    private CustomersPresenter presenter;
    private CustomersViewStub view;


    @Before
    public void setUp() {
        view = new CustomersViewStub();
        presenter = new CustomersPresenter(view);
        Initializer.resetAll();
    }

    /**
     * Tests the case of adding a customer.
     */
    @Test
    public void addCustomer() {
        presenter.addCustomer("FirstN", "LastN", "x@y.z", "6999999999");
        Assert.assertEquals("ok", view.getLog());
    }

    /**
     * Tests the case of editing a customer.
     */
    @Test
    public void editCustomer() {
        Customer customer = Initializer.getDummyCustomer();
        customer.add();

        presenter.updateCustomer(
                customer, "FirstN", "LastN", "x@y.z", "6999999999");
        Assert.assertEquals("ok", view.getLog());
    }

    /**
     * Tests the case of deleting a customer.
     */
    @Test
    public void deleteCustomer() {
        Customer customer = Initializer.getDummyCustomer();
        customer.add();

        presenter.deleteCustomer(customer.getEmailAddress().toString());
        Assert.assertNull(CustomersDAO.find(customer.getEmailAddress()));
    }


}

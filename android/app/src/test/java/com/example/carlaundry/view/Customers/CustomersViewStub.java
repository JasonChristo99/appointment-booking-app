package com.example.carlaundry.view.Customers;

import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.view.CleaningStuff.CleaningStuffView;

import java.time.DayOfWeek;
import java.util.Map;

public class CustomersViewStub implements CustomersView {
    private String log;

    public String getLog() {
        return log;
    }

    @Override
    public void navigateToCustomer() {
        log = "ok";
    }

    @Override
    public void navigateToCustomerAdd() {

    }

    @Override
    public void navigateToCustomerDetails(String emailAddress) {

    }

    @Override
    public void wrongTypeOfData() {
        log = "error";
    }
}

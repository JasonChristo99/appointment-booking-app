package com.example.carlaundry.view.Customers;

public interface CustomersView {
    void navigateToCustomer();

    void navigateToCustomerAdd();

    void navigateToCustomerDetails(String emailAddress);

    void wrongTypeOfData();

}

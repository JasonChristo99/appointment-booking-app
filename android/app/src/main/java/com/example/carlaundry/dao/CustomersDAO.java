package com.example.carlaundry.dao;

import com.example.carlaundry.domain.Customer;

import java.util.HashSet;
import java.util.Set;

public class CustomersDAO {
    private Set<Customer> customers = new HashSet<>();

    public Set<Customer> getCustomers() {
        return customers;
    }

    //TODO
    public static boolean addCustomer(Customer customer) {
        return false;
    }

    //TODO
    public static boolean removeCustomer(int customerId) {
        return false;
    }
}

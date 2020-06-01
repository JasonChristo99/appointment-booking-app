package com.example.carlaundry.dao;

import com.example.carlaundry.domain.Customer;
import com.example.carlaundry.util.EmailAddress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomersDAO {
    private static Set<Customer> customers = new HashSet<>();

    public static Set<Customer> getCustomers() {
        return customers;
    }

    public static Customer find(EmailAddress emailAddress) {
        for (Customer customer : customers) {
            if (customer.getEmailAddress().equals(emailAddress)) {
                return customer;
            }
        }
        return null;
    }

    public static boolean add(Customer customer) {
        return customers.add(customer);
    }

    public static boolean remove(Customer customer) {
        return customers.remove(customer);
    }

    public static void reset() {
        customers = new HashSet<>();
    }

    public static List<String> getCustomersStringsList() {
        List<String> strings = new ArrayList<>();
        for (Customer customer: customers) {
            strings.add(customer.getEmailAddress().toString());
        }
        Collections.sort(strings);
        return strings;
    }
}

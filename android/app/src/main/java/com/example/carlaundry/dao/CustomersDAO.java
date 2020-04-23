package com.example.carlaundry.dao;

import com.example.carlaundry.domain.Customer;

import java.util.HashSet;
import java.util.Set;

public class CustomersDAO {
    private static Set<Customer> customers = new HashSet<>();

    public static Set<Customer> getCustomers() {
        return customers;
    }

    public static Customer find(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
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
}

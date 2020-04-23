package com.example.carlaundry.dao;

public class Initializer {
    public static void resetAll() {
        AppointmentsDAO.reset();
        CleaningStuffDAO.reset();
        CleaningTypesDAO.reset();
        CustomersDAO.reset();
        UserAccountsDAO.reset();
    }

    //TODO create mock data
    void prepareData() {

    }
}

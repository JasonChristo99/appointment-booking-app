package com.example.carlaundry.dao;

import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.AppointmentState;
import com.example.carlaundry.domain.Car;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.CleaningType;
import com.example.carlaundry.domain.Customer;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.Money;
import com.example.carlaundry.util.TelephoneNumber;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

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

    public static Appointment getDummyAppointment() {
        return new Appointment(
                1,
                LocalDateTime.of(2020, 1, 1, 0, 0),
                LocalDateTime.of(2020, 1, 2, 0, 0),
                getDummyCustomer(),
                getDummyCleaningStuffMember(),
                getDummyCleaningType(),
                AppointmentState.PENDING,
                new Car("ABC123", "Manuf", "modelX"),
                "none"
        );
    }

    public static Customer getDummyCustomer() {
        return new Customer("Dum",
                "Cust",
                new TelephoneNumber("6999999999"),
                new EmailAddress("a@b.com"),
                1, LocalDate.of(2020, 1, 1)
        );
    }

    public static CleaningStuffMember getDummyCleaningStuffMember() {
        return new CleaningStuffMember(
                "Dum",
                "Stuf",
                new TelephoneNumber("6999999999"),
                new EmailAddress("a@b.com"), 1,
                LocalDate.of(2020, 1, 1)
        );
    }

    public static CleaningType getDummyCleaningType() {
        return new CleaningType(
                "bio",
                new Money(new BigDecimal(10), Currency.getInstance("EUR")),
                Duration.ofHours(1),
                1);
    }
}

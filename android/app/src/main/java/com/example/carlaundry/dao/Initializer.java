package com.example.carlaundry.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.Car;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.CleaningType;
import com.example.carlaundry.domain.Customer;
import com.example.carlaundry.domain.UserAccount;
import com.example.carlaundry.domain.WorkHours;
import com.example.carlaundry.util.AFM;
import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.Money;
import com.example.carlaundry.util.TelephoneNumber;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class Initializer {
    private static int sequence_id = 1, curr_hour = 1;

    public static void resetAll() {
        AppointmentsDAO.reset();
        CleaningStuffDAO.reset();
        CleaningTypesDAO.reset();
        CustomersDAO.reset();
        UserAccountsDAO.reset();
    }

    //TODO create mock data
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void prepareDataAlt() {

        // create admin account
        UserAccount adminAcc = getDummyAdminAccount();
        // create customers
        Customer cust1 = getDummyCustomer();
        cust1.add();
        Customer cust2 = getDummyCustomer();
        cust2.add();
        Customer cust3 = getDummyCustomer();
        cust3.add();

        // create cleaners
        CleaningStuffMember stuff1 = getDummyCleaningStuffMember();
        stuff1.hire();
        CleaningStuffMember stuff2 = getDummyCleaningStuffMember();
        stuff2.hire();
        CleaningStuffMember stuff3 = getDummyCleaningStuffMember();
        stuff3.hire();

        // create cleaning types
        CleaningType type1 = getDummyCleaningType();
        type1.add();
        CleaningType type2 = getDummyCleaningType();
        type2.add();
        CleaningType type3 = getDummyCleaningType();
        type3.add();


        Appointment apt1 = getDummyAppointment(cust1, stuff1, type1);
        apt1.schedule();
        Appointment apt2 = getDummyAppointment(cust1, stuff1, type1);
        apt2.schedule();
        Appointment apt3 = getDummyAppointment(cust2, stuff2, type2);
        apt3.schedule();
        Appointment apt4 = getDummyAppointment(cust2, stuff2, type2);
        apt4.schedule();
        Appointment apt5 = getDummyAppointment(cust3, stuff3, type3);
        apt5.schedule();
        Appointment apt6 = getDummyAppointment(cust3, stuff3, type3);
        apt6.schedule();


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Appointment getDummyAppointment(Customer cust, CleaningStuffMember stuff, CleaningType type) {
        return new Appointment(
                getNextId(),
                LocalDateTime.of(2020, 1, 1, 10 + getNextHour(), 0),
                cust,
                stuff,
                type,
                getDummyCar()
        );
    }

    private static int getNextHour() {
        curr_hour++;
        return curr_hour - 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Customer getDummyCustomer() {
        return new Customer("Customer",
                "Client",
                getDummyTelNo(),
                getDummyEmail(),
                LocalDate.of(2020, 1, 1)
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static CleaningStuffMember getDummyCleaningStuffMember() {
        return new CleaningStuffMember(
                "Cleaner" + getNextId(),
                "Stuff",
                getDummyTelNo(),
                getDummyEmail(),
                getDummyAFM(),
                LocalDate.of(2020, 1, 1),
                getDummyWorkHours()
        );
    }

    private static AFM getDummyAFM() {
        return new AFM("123456789");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static CleaningType getDummyCleaningType() {
        return new CleaningType(
                "Type" + getNextId(),
                getDummyMoney(),
                Duration.ofHours(1));
    }

    public static Car getDummyCar() {
        return new Car("ABC123", "Manuf");
    }

    public static EmailAddress getDummyEmail() {
        return new EmailAddress("acc" + getNextId() + "@mail.com");
    }

    private static int getNextId() {
        sequence_id++;
        return sequence_id - 1;
    }

    public static TelephoneNumber getDummyTelNo() {
        return new TelephoneNumber("6999999999");
    }

    public static Money getDummyMoney() {
        return new Money(10, Currency.getInstance("EUR"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static WorkHours getDummyWorkHours() {
        Map<DayOfWeek, DailyTimePeriod> map = new HashMap<>();
        map.put(DayOfWeek.WEDNESDAY, new DailyTimePeriod(LocalTime.of(8, 0), LocalTime.of(16, 0)));
        return new WorkHours(map);
    }

    public static UserAccount getDummyAdminAccount() {
        return new UserAccount(new EmailAddress("admin@mail.com"), UserAccount.AccountType.ADMIN);
    }
}

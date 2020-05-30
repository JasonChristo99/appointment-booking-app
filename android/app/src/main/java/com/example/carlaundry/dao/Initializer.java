package com.example.carlaundry.dao;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.carlaundry.domain.Appointment;
import com.example.carlaundry.domain.AppointmentState;
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
    public static void prepareData() {
        // create admin account
        UserAccount adminAcc = getDummyAdminAccount();
        Appointment apt1 = getDummyAppointment();
        apt1.schedule();
        Appointment apt2 = getDummyAppointment();
        apt2.schedule();
        Appointment apt3 = getDummyAppointment();
        apt3.schedule();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Appointment getDummyAppointment() {
        return new Appointment(
                getNextId(),
                LocalDateTime.of(2020, 1, 1, 10 + getNextHour(), 0),
                getDummyCustomer(),
                getDummyCleaningStuffMember(),
                getDummyCleaningType(),
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
                "Cleaner",
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
                "Biological",
                getDummyMoney(),
                Duration.ofHours(1),
                1);
    }

    public static Car getDummyCar() {
        return new Car("ABC123", "Manuf", "modelX");
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

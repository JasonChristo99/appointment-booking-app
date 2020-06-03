package com.example.carlaundry.view.CleaningStuff;

import android.os.Build;
import android.provider.ContactsContract;

import androidx.annotation.RequiresApi;

import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.UserAccountsDAO;
import com.example.carlaundry.domain.CleaningStuffMember;
import com.example.carlaundry.domain.UserAccount;
import com.example.carlaundry.domain.WorkHours;
import com.example.carlaundry.util.AFM;
import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;
import com.example.carlaundry.view.Customers.CustomersViewEdit;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CleaningStuffPresenter {

    private CleaningStuffView cleaningStuffView;

    public CleaningStuffPresenter(CleaningStuffView cleaningStuffView) {
        this.cleaningStuffView = cleaningStuffView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addCleaningStuff(String firstName, String lastName, String stringtelephone, String stringmail, String stringafm) {
        try {
            EmailAddress emailAddress = new EmailAddress(stringmail);
            TelephoneNumber telephoneNumber = new TelephoneNumber(stringtelephone);
            AFM afm = new AFM(stringafm);
            Map<DayOfWeek,DailyTimePeriod> map = new HashMap<>();
            CleaningStuffDAO.add(new CleaningStuffMember(firstName, lastName, telephoneNumber, emailAddress, afm, LocalDate.now(), new WorkHours(map)));
            cleaningStuffView.navigateToCleaningStuffActivity();
        }catch(IllegalArgumentException e){
            cleaningStuffView.wrongData();
        }
    }

    public void updateCleaningStuff(CleaningStuffMember cleaner, String firstName, String lastName, String telephone, String stringmail, String oldStringEmail, String stringafm) {

        try {
            EmailAddress email = new EmailAddress(stringmail);
            AFM afm = new AFM(stringafm);
            TelephoneNumber telNo = new TelephoneNumber(telephone);
            cleaner.setFirstName(firstName);
            cleaner.setLastName(lastName);
            cleaner.setTelNo(telNo);
            cleaner.setEmailAddress(email);
            cleaner.setAfm(afm);
            EmailAddress oldEmail = new EmailAddress(oldStringEmail);
            UserAccountsDAO.remove(UserAccountsDAO.find(oldEmail));
            UserAccountsDAO.add(new UserAccount(email, UserAccount.AccountType.STUFF));

            cleaningStuffView.navigateToCleaningStuffActivity();
        } catch (IllegalArgumentException e) {
            cleaningStuffView.wrongData();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void updateHours(String stringmail, LocalTime startHours, LocalTime finishHours, int day) {
        CustomersViewEdit customersViewEdit = new CustomersViewEdit();

        EmailAddress email = new EmailAddress(stringmail);
        CleaningStuffMember cleaner = CleaningStuffDAO.find(email);
        WorkHours workHours = cleaner.getWorkHours();
        DailyTimePeriod dtp = new DailyTimePeriod(startHours, finishHours);
        LocalTime testTime = LocalTime.of(0,0,0);
        if (!(startHours.compareTo(testTime)==0 && (finishHours.compareTo(testTime)==0))) {
            workHours.getWorkHoursMap().put(DayOfWeek.of(day), dtp);
        }else{
            workHours.getWorkHoursMap().remove(DayOfWeek.of(day));
        }
    }

    public void deleteCleaner(String stringmail) {
        EmailAddress email = new EmailAddress(stringmail);
        CleaningStuffMember cleaner = CleaningStuffDAO.find(email);
        cleaner.fire();
    }
}

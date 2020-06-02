package com.example.carlaundry.domain;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.carlaundry.dao.AppointmentsDAO;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.dao.UserAccountsDAO;
import com.example.carlaundry.util.AFM;
import com.example.carlaundry.util.DailyTimePeriod;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.util.TelephoneNumber;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CleaningStuffMember extends Person {
    private AFM afm;
    private LocalDate dateHired;
    private WorkHours workHours;
    private UserAccount userAccount;

    public CleaningStuffMember(String firstName, String lastName, TelephoneNumber telNumber, EmailAddress emailAddress, AFM afm, LocalDate dateHired, WorkHours workHours) {
        super(firstName, lastName, telNumber, emailAddress);
        this.afm = afm;
        this.dateHired = dateHired;
        this.workHours = workHours;
        userAccount = new UserAccount(emailAddress, UserAccount.AccountType.STUFF);
    }

    public AFM getAfm() {
        return afm;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cleaner " + getEmailAddress() + "(" + getWorkHours().getWorkHoursMap() + ")";
    }

    public Set<Appointment> getAssignedPendingAppointments() {
        Set<Appointment> aptSet = new HashSet<>();
        for (Appointment apt : AppointmentsDAO.getAppointments()) {
            if (this.equals(apt.getStuffMember()) && apt.getAptState().equals(AppointmentState.PENDING)) {
                aptSet.add(apt);
            }
        }
        return aptSet;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isAvailableOn(LocalDateTime aptDate) {
        // works on date
        boolean worksOnDate = false;
        for (Map.Entry<DayOfWeek, DailyTimePeriod> entry : workHours.getWorkHoursMap().entrySet()) {
            if (aptDate.getDayOfWeek().equals(entry.getKey())) {
                if (aptDate.toLocalTime().isAfter(entry.getValue().getStartHour()) && aptDate.toLocalTime().isBefore(entry.getValue().getEndHour())) {
                    worksOnDate = true;
                    break;
                }
            }
        }
        if (!worksOnDate) {
            System.out.println("Cleaner unavailable 1");
            return false;
        }
        // is available on date
        for (Appointment appointment : getAssignedPendingAppointments()) {
            if (aptDate.isAfter(appointment.getAptDate()) && aptDate.isBefore(appointment.getAptDate().plus(appointment.getCleaningType().getEstimatedDuration()))) {
                System.out.println("Cleaner unavailable 2");
                return false;
            }
        }
        return true;
    }

    public boolean hire() {
        return CleaningStuffDAO.add(this);
    }

    public boolean fire() {
        if (CleaningStuffDAO.remove(this)) {
            return userAccount.delete();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getEmailAddress().hashCode();
    }

    public boolean isHired() {
        return CleaningStuffDAO.find(getEmailAddress()) != null;
    }
}

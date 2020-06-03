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

    public void setAfm(AFM afm) {
        this.afm = afm;
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

    /**
     * Gathers the pending appointments for this cleaner and returns it as a set.
     * @return the appointment set
     */
    public Set<Appointment> getAssignedPendingAppointments() {
        Set<Appointment> aptSet = new HashSet<>();
        for (Appointment apt : AppointmentsDAO.getAppointments()) {
            if (this.equals(apt.getStuffMember()) && apt.getAptState().equals(AppointmentState.PENDING)) {
                aptSet.add(apt);
            }
        }
        return aptSet;
    }

    /**
     * Considering a given date, this method checks whether the cleaner is available,
     * meaning it is on their working hours and they have no other appointment on the same date.
     * Based on this answer, an appointment can be scheduled with this cleaner.
     * @param aptDate the date to check availability for
     * @return true if cleaner is available on date
     */
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

    /**
     * Adds the cleaner to the DAO.
     * @return true if added successfully
     */
    public boolean hire() {
        return CleaningStuffDAO.add(this);
    }

    /**
     * Removes the cleaner from the DAO.
     * @return true if removed successfully
     */
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

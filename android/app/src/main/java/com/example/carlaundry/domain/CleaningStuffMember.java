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

    public CleaningStuffMember(String firstName, String lastName, TelephoneNumber telNumber, EmailAddress emailAddress, int id, AFM afm, LocalDate dateHired, WorkHours workHours) {
        super(firstName, lastName, telNumber, emailAddress, id);
        this.afm = afm;
        this.dateHired = dateHired;
        this.workHours = workHours;
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

    @NonNull
    @Override
    public String toString() {
        return "Cleaner " + getId() + "(" + getWorkHours().getWorkHoursMap() + ")";
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
            return false;
        }
        // is available on date
        for (Appointment appointment : getAssignedPendingAppointments()) {
            if (aptDate.isAfter(appointment.getAptDate()) && aptDate.isBefore(appointment.getAptDate().plus(appointment.getCleaningType().getEstimatedDuration()))) {
                return false;
            }
        }
        return true;
    }

    public boolean hire() {
        if (CleaningStuffDAO.find(this.getId()) == null) {
            return CleaningStuffDAO.add(this);
        }
        return false;
    }

    public boolean fire() {
        if (CleaningStuffDAO.remove(this)) {
            return UserAccountsDAO.findStuff(this.getId()).delete();
        }
        return false;
    }
}
